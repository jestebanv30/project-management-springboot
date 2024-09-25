package com.juanesdev.project_management.domain.dto;

import com.juanesdev.project_management.domain.enums.Role;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UserProfileResponseDto {
    private String idUser;
    private String name;
    private String email;
    private String username;
    private Role role;

    private Long approvedProjectsCount;
    private Long rejectedProjectsCount;
    private Long pendingProjectsCount;
}
