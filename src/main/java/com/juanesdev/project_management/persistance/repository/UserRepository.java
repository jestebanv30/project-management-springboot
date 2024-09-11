package com.juanesdev.project_management.persistance.repository;

import com.juanesdev.project_management.domain.dto.UserDto;
import com.juanesdev.project_management.domain.repository.IUserRepository;
import com.juanesdev.project_management.persistance.crud.IUserCrud;
import com.juanesdev.project_management.persistance.entity.UserEntity;
import com.juanesdev.project_management.persistance.mapper.IUserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class UserRepository implements IUserRepository {

    private final IUserCrud iUserCrud;
    private final IUserMapper iUserMapper;

    @Override
    public List<UserDto> getAll() {
        return iUserMapper.toUserDtoList(iUserCrud.findAll());
    }

    @Override
    public Optional<UserDto> getByIdCar(String id) {
        return iUserCrud.findById(id).map(iUserMapper::toUserDto);
    }

    @Override
    public Optional<UserDto> getByEmail(String email) {
        return iUserCrud.findByEmailIgnoreCase(email).map(iUserMapper::toUserDto);
    }

    @Override
    public UserDto save(UserDto userDto) {
        UserEntity userEntity = iUserCrud.save(iUserMapper.toUserEntity(userDto));
        return iUserMapper.toUserDto(userEntity);
    }

    @Override
    public void deleteById(String id) {
        iUserCrud.deleteById(id);
    }
}
