package com.juanesdev.project_management.domain.usecase;

import com.juanesdev.project_management.domain.dto.CourseDto;

import java.util.List;
import java.util.Optional;

public interface ICourseUseCase {
    List<CourseDto> getAll();
    Optional<CourseDto> getByIdCourse(Integer id);
    Optional<CourseDto> getByTitle(String title);
    CourseDto save(CourseDto courseDto);
    Optional<CourseDto> update(CourseDto courseDto);
    boolean deleteById(Integer id);
}
