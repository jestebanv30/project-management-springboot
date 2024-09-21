package com.juanesdev.project_management.domain.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter @Setter
public class CourseDto {
    private Integer idCourse;
    private String teacherId;
    private String title;
    private Long totalProjects;

    public CourseDto(Integer idCourse, String teacherId, String title, Long totalProjects) {
        this.idCourse = idCourse;
        this.teacherId = teacherId;
        this.title = title;
        this.totalProjects = totalProjects;
    }
}