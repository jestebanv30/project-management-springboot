package com.juanesdev.project_management.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.juanesdev.project_management.domain.dto.UserDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import org.springframework.security.core.Authentication;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

@Component
public class JwtAuthenticationProvider {

    //Llave para cifrar el jwt
    @Value("${jwt.secret.key}")
    private String secretKey;

    private HashMap<String, UserDto> listToken = new HashMap<>();

    private Set<String> revokedToken = new HashSet<>();

    public String createToken(UserDto user) {
        Date now = new Date();
        Date validity = new Date(now.getTime() + 3600000);
        Algorithm algorithm = Algorithm.HMAC256(secretKey); //Algoritmo de encriptacion y el HMAC necesita una llave secreta

        String tokenCreated = JWT.create()
                .withClaim("idUser", user.getIdUser())
                .withClaim("name", user.getName())
                .withClaim("email", user.getEmail())
                .withClaim("username", user.getUsername())
                .withClaim("role", String.valueOf(user.getRole()))
                .withIssuedAt(now)
                .withExpiresAt(validity)
                .sign(algorithm);

        listToken.put(tokenCreated, user);
        return tokenCreated;
    }

    public Authentication validateToken(String token) throws AuthenticationException {
        //Verifia si el token esta en la lista de tokens revocados
        if (revokedToken.contains(token)) {
            throw new BadCredentialsException("Token revocado");
        }

        //Verifica la firma del token y expiracion
        JWT.require(Algorithm.HMAC256(secretKey)).build().verify(token);

        UserDto user = listToken.get(token);
        //Verifica si el token pertenece a un usuario
        if (user == null) {
            throw new BadCredentialsException("Usuario no registrado");
        }

        HashSet<SimpleGrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_" + user.getRole()));

        //Despues de validar el token me auentico y eso lo guardo en el CONTEXT
        return new UsernamePasswordAuthenticationToken(user, token, authorities);
    }

    public String deleteToken(String token) {
        if (!listToken.containsKey(token)) {
            throw new BadCredentialsException("No existe el token");
        }

        revokedToken.add(token);
        listToken.remove(token);

        return "Sesión cerrada exitosamente";
    }
}
