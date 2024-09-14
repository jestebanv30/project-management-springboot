package com.juanesdev.project_management.domain.repository;

import com.juanesdev.project_management.domain.dto.UserDto;

import java.util.List;
import java.util.Optional;

public interface IUserRepository {
    List<UserDto> getAll();
    Optional<UserDto> getByIdCar(String id);
    Optional<UserDto> getByEmail(String email);
    UserDto save(UserDto userDto);
    void deleteById(String id);
}
