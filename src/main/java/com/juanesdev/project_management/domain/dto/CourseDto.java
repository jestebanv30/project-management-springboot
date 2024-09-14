package com.juanesdev.project_management.domain.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CourseDto {
    private Integer idCourse;
    private String teacherId;
    private String title;
}