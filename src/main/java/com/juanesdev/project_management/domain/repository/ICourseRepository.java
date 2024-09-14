package com.juanesdev.project_management.domain.repository;

import com.juanesdev.project_management.domain.dto.CourseDto;

import java.util.List;
import java.util.Optional;

public interface ICourseRepository {
    List<CourseDto> getAll();
    Optional<CourseDto> getByIdCourse(Integer id);
    Optional<CourseDto> getByTitle(String title);
    CourseDto save(CourseDto courseDto);
    void deleteById(Integer id);
}
