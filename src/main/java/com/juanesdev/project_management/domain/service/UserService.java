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
            throw new RuntimeException("Usuario no encontrado");
        }

        return userDto;
    }

    @Override
    public Optional<UserDto> getByEmail(String email) {
        Optional<UserDto> userDto = iUserRepository.getByEmail(email);

        if (userDto.isEmpty()) {
            throw new RuntimeException("Usuario no encontrado");
        }

        return userDto;
    }

    @Override
    public UserDto save(UserDto userDto) {
        return iUserRepository.save(userDto);
    }

    @Override
    public Optional<UserDto> update(UserDto userDto) {
        Optional<UserDto> existingUserDto = getByIdCar(userDto.getIdUser());

        if (existingUserDto.isEmpty()) {
            throw new RuntimeException("Usuario no encontrado");
        }

        Optional<UserDto> existingEmailDto = getByEmail(userDto.getEmail());

        if (existingEmailDto.isPresent() && !existingEmailDto.get().getIdUser().equals(userDto.getIdUser())) {
            throw new RuntimeException("El email ya pertenece a una cuenta");
        }

        return Optional.of(iUserRepository.save(userDto));
    }

    @Override
    public boolean deleteById(String id) {
        Optional<UserDto> userDto = iUserRepository.getByIdCar(id);

        if (userDto.isEmpty()) {
            throw new RuntimeException("Usuario no encontrado");
        }

        iUserRepository.deleteById(id);
        return true;
    }
}
