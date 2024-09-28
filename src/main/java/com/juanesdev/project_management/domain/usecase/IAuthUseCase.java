package com.juanesdev.project_management.domain.usecase;

import com.juanesdev.project_management.domain.dto.AuthUserDto;
import com.juanesdev.project_management.domain.dto.JwtResponseDto;

public interface IAuthUseCase {

    JwtResponseDto signIn (AuthUserDto authUserDto);

    JwtResponseDto signOut(String jwt);
}
