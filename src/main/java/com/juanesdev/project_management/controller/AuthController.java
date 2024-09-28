package com.juanesdev.project_management.controller;

import com.juanesdev.project_management.domain.dto.AuthUserDto;
import com.juanesdev.project_management.domain.dto.JwtResponseDto;
import com.juanesdev.project_management.domain.dto.UserDto;
import com.juanesdev.project_management.domain.dto.UserResponseDto;
import com.juanesdev.project_management.domain.usecase.IAuthUseCase;
import com.juanesdev.project_management.domain.usecase.IUserUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/auth")
public class AuthController {

    private final IAuthUseCase iAuthUseCase;

    private final IUserUseCase iUserUseCase;

    @PostMapping(path = "/register")
    public ResponseEntity<UserResponseDto> register(@RequestBody UserDto userDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(iUserUseCase.save(userDto));
    }

    @PostMapping(path = "/sign-in")
    public ResponseEntity<JwtResponseDto> signIn(@RequestBody AuthUserDto authUserDto) {
        return ResponseEntity.ok(iAuthUseCase.signIn(authUserDto));
    }

    @PostMapping(path = "/sign-out")
    public ResponseEntity<JwtResponseDto> signOut(@RequestHeader(name = HttpHeaders.AUTHORIZATION) String token) {
        return ResponseEntity.ok(iAuthUseCase.signOut(token));
    }
}
