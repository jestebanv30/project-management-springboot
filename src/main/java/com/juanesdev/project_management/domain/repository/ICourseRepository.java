package com.juanesdev.project_management.domain.repository;

import com.juanesdev.project_management.domain.dto.CourseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ICourseRepository {
    List<CourseDto> getAll();
    Optional<CourseDto> getByIdCourse(Integer id);
    CourseDto save(CourseDto courseDto);
    void deleteById(Integer id);
    List<CourseDto> getCoursesWithProjectCount();
}
