package com.juanesdev.project_management.domain.service;

import com.juanesdev.project_management.domain.dto.AuthUserDto;
import com.juanesdev.project_management.domain.dto.JwtResponseDto;
import com.juanesdev.project_management.domain.dto.UserDto;
import com.juanesdev.project_management.domain.repository.IUserRepository;
import com.juanesdev.project_management.domain.usecase.IAuthUseCase;
import com.juanesdev.project_management.security.JwtAuthenticationProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class AuthService implements IAuthUseCase {

    private final IUserRepository iUserRepository;

    private final JwtAuthenticationProvider jwtAuthenticationProvider;

    private final PasswordEncoder passwordEncoder;

    @Override
    public JwtResponseDto signIn(AuthUserDto authUserDto) {

        Optional<UserDto> user = iUserRepository.getByUsername(authUserDto.getUsername());

        if (user.isEmpty()) {
            throw new RuntimeException("El usuario no existe");
        }
        if (!passwordEncoder.matches(authUserDto.getPassword(), user.get().getPassword())) {
            throw new RuntimeException("La contraseña es incorrecta");
        }

        return new JwtResponseDto(jwtAuthenticationProvider.createToken(user.get()));
    }

    @Override
    public JwtResponseDto signOut(String jwt) {

        String[] authElements = jwt.split(" ");
        return new JwtResponseDto(jwtAuthenticationProvider.deleteToken(authElements[1]));
    }
}
