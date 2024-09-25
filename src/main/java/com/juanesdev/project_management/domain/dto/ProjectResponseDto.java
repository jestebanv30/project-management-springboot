package com.juanesdev.project_management.domain.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class ProjectResponseDto {
    private String id;
    private String title;
    private String description;
    private String studentName;
    private String courseName;
    private String timeAgo;
    private List<String> images;
}
