package com.juanesdev.project_management.persistance.mapper;

import com.juanesdev.project_management.domain.dto.UserDto;
import com.juanesdev.project_management.persistance.entity.UserEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IUserMapper {
    UserDto toUserDto(UserEntity userEntity);

    UserEntity toUserEntity(UserDto userDto);

    List<UserDto> toUserDtoList(List<UserEntity> userEntityList);
}
