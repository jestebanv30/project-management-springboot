package com.juanesdev.project_management.domain.usecase;

import com.juanesdev.project_management.domain.dto.UserDto;

import java.util.List;
import java.util.Optional;

public interface IUserUseCase {
    List<UserDto> getAll();
    Optional<UserDto> getByIdCar(String id);
    Optional<UserDto> getByEmail(String email);
    UserDto save(UserDto userDto);
    UserDto update(UserDto userDto);
    boolean deleteById(String id);
}
