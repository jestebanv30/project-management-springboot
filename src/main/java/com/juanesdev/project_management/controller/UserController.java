package com.juanesdev.project_management.controller;

import com.juanesdev.project_management.domain.dto.UserDto;
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

    @GetMapping(path = "/{idCar}")
    public ResponseEntity<UserDto> getById(@PathVariable String idCar) {
        return ResponseEntity.of(iUserUseCase.getByIdCar(idCar));
    }

    @GetMapping(path = "/email/{email}")
    public ResponseEntity<UserDto> getByEmail(@PathVariable String email) {
        return ResponseEntity.of(iUserUseCase.getByEmail(email));
    }

    @PostMapping()
    public ResponseEntity<UserDto> create(@RequestBody UserDto userDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(iUserUseCase.save(userDto));
    }

    @PutMapping()
    public ResponseEntity<UserDto> update(@RequestBody UserDto userDto) {
        return ResponseEntity.of(iUserUseCase.update(userDto));
    }

    @DeleteMapping(path = "/{idCar}")
    public ResponseEntity<Void> delete(@PathVariable String idCar) {
        return new ResponseEntity<>(this.iUserUseCase.deleteById(idCar) ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }
}
