package com.juanesdev.project_management.domain.service;

import com.juanesdev.project_management.domain.dto.UserDto;
import com.juanesdev.project_management.domain.dto.UserProfileResponseDto;
import com.juanesdev.project_management.domain.dto.UserResponseDto;
import com.juanesdev.project_management.domain.enums.ProjectStatus;
import com.juanesdev.project_management.domain.enums.Role;
import com.juanesdev.project_management.domain.repository.IProjectRepository;
import com.juanesdev.project_management.domain.repository.IUserRepository;
import com.juanesdev.project_management.domain.usecase.IUserUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService implements IUserUseCase {

    private final IUserRepository iUserRepository;
    private final IProjectRepository iProjectRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public List<UserDto> getAll() {
        return iUserRepository.getAll();
    }

    @Override
    public Optional<UserProfileResponseDto> getByIdUser(String id) {
        return iUserRepository.getByIdUser(id).map(userDto -> {
            UserProfileResponseDto userProfileResponseDto = new UserProfileResponseDto();

            userProfileResponseDto.setIdUser(userDto.getIdUser());
            userProfileResponseDto.setName(userDto.getName());
            userProfileResponseDto.setEmail(userDto.getEmail());
            userProfileResponseDto.setUsername(userDto.getUsername());
            userProfileResponseDto.setRole(userDto.getRole());

            Long approvedCount = iProjectRepository.countByStudentIdAndStatus(userDto.getIdUser(), ProjectStatus.APPROVED);
            Long rejectedCount = iProjectRepository.countByStudentIdAndStatus(userDto.getIdUser(), ProjectStatus.REJECTED);
            Long pendingCount = iProjectRepository.countByStudentIdAndStatus(userDto.getIdUser(), ProjectStatus.PENDING);

            userProfileResponseDto.setApprovedProjectsCount(approvedCount);
            userProfileResponseDto.setRejectedProjectsCount(rejectedCount);
            userProfileResponseDto.setPendingProjectsCount(pendingCount);

            return userProfileResponseDto;
        });
    }

    @Override
    public Optional<UserDto> getByUsername(String username) {
        Optional<UserDto> userDto = iUserRepository.getByUsername(username);

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
    public UserResponseDto save(UserDto userDto) {
        String email = userDto.getEmail();
        validateEmailDomain(email);

        //Extraer el nombre de usuario del correo antes de la '@'
        String username = email.substring(0, email.indexOf('@'));
        userDto.setUsername(username);

        Optional<UserDto> existingIdUser = iUserRepository.getByIdUser(userDto.getIdUser());
        Optional<UserDto> existingEmailUser = iUserRepository.getByEmail(userDto.getEmail());
        Optional<UserDto> existingUsername = iUserRepository.getByUsername(userDto.getUsername());

        if (existingIdUser.isPresent() || existingEmailUser.isPresent() || existingUsername.isPresent()) {
            throw new RuntimeException("Usuario existente");
        }

        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));

        UserDto savedUserDto = iUserRepository.save(userDto);

        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setUsername(savedUserDto.getUsername());

        return userResponseDto;
    }

    @Override
    public Optional<UserDto> update(UserDto userDto) {
        Optional<UserDto> existingUserDto = iUserRepository.getByIdUser(userDto.getIdUser());

        if (existingUserDto.isEmpty()) {
            throw new RuntimeException("Usuario no encontrado");
        }

        Optional<UserDto> existingEmailDto = getByEmail(userDto.getEmail());

        if (existingEmailDto.isPresent() && !existingEmailDto.get().getIdUser().equals(userDto.getIdUser())) {
            throw new RuntimeException("El email ya pertenece a una cuenta");
        }

        validateEmailDomain(userDto.getEmail());
        validateUsername(userDto.getEmail(), userDto.getUsername());

        return Optional.of(iUserRepository.save(userDto));
    }

    @Override
    public boolean deleteById(String id) {
        Optional<UserDto> userDto = iUserRepository.getByIdUser(id);

        if (userDto.isEmpty()) {
            throw new RuntimeException("Usuario no encontrado");
        }

        iUserRepository.deleteById(id);
        return true;
    }

    private void validateEmailDomain(String email) {
        String emailDomain = "@unicesar.edu.co";

        if (!email.endsWith(emailDomain)) {
            throw new RuntimeException("El email debe pertenecer al dominio " +emailDomain);
        }
    }

    private void validateUsername(String email, String username) {
        String userNameEmail = email.substring(0, email.indexOf('@'));

        if (!userNameEmail.equals(username)){
            throw new RuntimeException("El nombre de usuario no concuerda con el email");
        }
    }
}
