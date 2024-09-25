package com.juanesdev.project_management.domain.dto;

import com.juanesdev.project_management.domain.enums.ProjectStatus;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class ProjectResponseDto {
    private Integer id;
    private String title;
    private String description;
    private String studentName;
    private String courseName;
    private ProjectStatus status;
    private String timeAgo;
    private List<String> images;
}
