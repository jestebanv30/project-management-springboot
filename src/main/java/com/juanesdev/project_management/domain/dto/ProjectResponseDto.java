package com.juanesdev.project_management.domain.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ProjectResponseDto {
    private String title;
    private String description;
    private String studentName;
    private String courseName;
    private String timeAgo;
}
