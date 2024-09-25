package com.juanesdev.project_management.domain.dto;

import com.juanesdev.project_management.domain.enums.ProjectStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter @Setter
public class ProjectDto {
    private Integer idProject;
    private String studentId;
    private Integer courseId;
    private String title;
    private String description;
    private ProjectStatus status;
    private String filesUrl;
    private LocalDateTime createdAt;
}
