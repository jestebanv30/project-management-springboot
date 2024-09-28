package com.juanesdev.project_management.controller;

import com.juanesdev.project_management.domain.dto.UserDto;
import com.juanesdev.project_management.domain.dto.UserProfileResponseDto;
import com.juanesdev.project_management.domain.dto.UserResponseDto;
import com.juanesdev.project_management.domain.usecase.IUserUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/user")
public class UserController {

    private final IUserUseCase iUserUseCase;

    @GetMapping
    public ResponseEntity<List<UserDto>> getAll() {
        return ResponseEntity.ok().body(iUserUseCase.getAll());
    }

    @GetMapping(path = "/{idUser}")
    public ResponseEntity<UserProfileResponseDto> getById(@PathVariable String idUser) {
        return ResponseEntity.of(iUserUseCase.getByIdUser(idUser));
    }

    @GetMapping(path = "/email/{email}")
    public ResponseEntity<UserDto> getByEmail(@PathVariable String email) {
        return ResponseEntity.of(iUserUseCase.getByEmail(email));
    }

    @GetMapping(path = "/username/{username}")
    public ResponseEntity<UserDto> getByUsername(@PathVariable String username) {
        return ResponseEntity.of(iUserUseCase.getByUsername(username));
    }

    @PostMapping()
    public ResponseEntity<UserResponseDto> create(@RequestBody UserDto userDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(iUserUseCase.save(userDto));
    }

    @PutMapping()
    public ResponseEntity<UserDto> update(@RequestBody UserDto userDto) {
        return ResponseEntity.of(iUserUseCase.update(userDto));
    }

    @DeleteMapping(path = "/{idUser}")
    public ResponseEntity<Void> delete(@PathVariable String idUser) {
        return new ResponseEntity<>(this.iUserUseCase.deleteById(idUser) ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }
}
