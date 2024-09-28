package com.juanesdev.project_management.domain.usecase;

import com.juanesdev.project_management.domain.dto.UserDto;
import com.juanesdev.project_management.domain.dto.UserProfileResponseDto;
import com.juanesdev.project_management.domain.dto.UserResponseDto;

import java.util.List;
import java.util.Optional;

public interface IUserUseCase {
    List<UserDto> getAll();

    Optional<UserProfileResponseDto> getByIdUser(String id);

    Optional<UserDto> getByUsername(String username);

    Optional<UserDto> getByEmail(String email);

    UserResponseDto save(UserDto userDto);

    Optional<UserDto> update(UserDto userDto);

    boolean deleteById(String id);
}
