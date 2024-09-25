package com.juanesdev.project_management.domain.dto;

import com.juanesdev.project_management.domain.enums.Role;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UserDto {
    private String idUser;
    private String name;
    private String email;
    private String username;
    private String password;
    private Role role;
}
