package com.juanesdev.project_management.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

//Clase que valida la solcitud http
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter { //clase abstracta que permite la personalizacion del filtro

    private final JwtAuthenticationProvider jwtAuthenticationProvider;

    private List<String> urlToSkip = List.of("/auth");

    //Verifica si la uri debe ser filtrada o no
    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        System.out.println("Entre aqui");
        System.out.println(request.getRequestURI());
        //Devuelve true la lista urlToSkip contiene la uri
        return urlToSkip.stream().anyMatch(url -> request.getRequestURI().contains(url));
    }

    //Aplica el filtro para verificar si una solicitud contiene un jwt válido y autentica al usuario
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String header = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (header == null) {
            throw new ServletException("Sin cabezera de autenticacion");
        }

        String[] authElements = header.split(" ");

        if (authElements.length != 2 || !"Bearer".equals(authElements[0])) {
            throw new ServletException("Sin Baerer Token");
        }

        try {
            Authentication auth =  jwtAuthenticationProvider.validateToken(authElements[1]);

            SecurityContextHolder.getContext().setAuthentication(auth);

            System.out.println("Voy a imprimir el context");
            System.out.println(SecurityContextHolder.getContext());
            System.out.println("Voy a imprimir la autenticacion");
            System.out.println(SecurityContextHolder.getContext().getAuthentication());
        } catch (RuntimeException e) {
            SecurityContextHolder.clearContext();
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Token invalido");
            return;
        }
        filterChain.doFilter(request, response);
    }
}
