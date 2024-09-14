package com.juanesdev.project_management.domain.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
public class ProjectDto {
    private String idProject;
    private String studentId;
    private String courseId;
    private String title;
    private String description;
    private LocalDateTime deliveryDate;
    private String status;
    private String filesUrl;
}
