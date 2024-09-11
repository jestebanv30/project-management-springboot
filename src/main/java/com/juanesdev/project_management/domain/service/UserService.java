package com.juanesdev.project_management.domain.service;

import com.juanesdev.project_management.domain.dto.UserDto;
import com.juanesdev.project_management.domain.repository.IUserRepository;
import com.juanesdev.project_management.domain.usecase.IUserUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService implements IUserUseCase {

    private final IUserRepository iUserRepository;

    @Override
    public List<UserDto> getAll() {
        return iUserRepository.getAll();
    }

    @Override
    public Optional<UserDto> getByIdCar(String id) {
        Optional<UserDto> userDto = iUserRepository.getByIdCar(id);

        if (userDto.isEmpty()) {
            System.out.println("La cedula del usuario no existe");
        }

        return userDto;
    }

    @Override
    public Optional<UserDto> getByEmail(String email) {
        Optional<UserDto> userDto = iUserRepository.getByEmail(email);

        if (userDto.isEmpty()) {
            System.out.println("El email del usario no existe");
        }

        return userDto;
    }

    @Override
    public UserDto save(UserDto userDto) {
        return iUserRepository.save(userDto);
    }

    @Override
    public UserDto update(UserDto userDto) {
        getByIdCar(userDto.getIdUser());
        return iUserRepository.save(userDto);
    }

    @Override
    public boolean deleteById(String id) {
        return false;
    }
}
