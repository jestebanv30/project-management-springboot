package com.juanesdev.project_management.domain.usecase;

import com.juanesdev.project_management.domain.dto.UserDto;

import java.util.List;
import java.util.Optional;

public interface IUserUseCase {
    List<UserDto> getAll();
    Optional<UserDto> getByIdUser(String id);
    Optional<UserDto> getByUsername(String username);
    Optional<UserDto> getByEmail(String email);
    UserDto save(UserDto userDto);
    Optional<UserDto> update(UserDto userDto);
    boolean deleteById(String id);
}
