package com.juanesdev.project_management.domain.service;

import com.juanesdev.project_management.domain.dto.CourseDto;
import com.juanesdev.project_management.domain.repository.ICourseRepository;
import com.juanesdev.project_management.domain.usecase.ICourseUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CourseService implements ICourseUseCase {

    private final ICourseRepository iCourseRepository;

    @Override
    public List<CourseDto> getAll() {
        return iCourseRepository.getAll();
    }

    @Override
    public Optional<CourseDto> getByIdCourse(Integer id) {
        Optional<CourseDto> courseDto = iCourseRepository.getByIdCourse(id);

        if (courseDto.isEmpty()) {
            throw new RuntimeException("Curso no encontrado");
        }

        return courseDto;
    }

    @Override
    public Optional<CourseDto> getByTitle(String title) {
        Optional<CourseDto> courseDtoByTitle = iCourseRepository.getByTitle(title);

        if (courseDtoByTitle.isEmpty()) {
            throw new RuntimeException("Curso no encontrado");
        }

        return courseDtoByTitle;
    }

    @Override
    public CourseDto save(CourseDto courseDto) {
        Optional<CourseDto> courseDtoById = iCourseRepository.getByIdCourse(courseDto.getIdCourse());
        Optional<CourseDto> courseDtoByTitle = iCourseRepository.getByTitle(courseDto.getTitle());

        if (courseDtoById.isPresent() || courseDtoByTitle.isPresent()) {
            throw new RuntimeException("Curso ya existente");
        }

        return iCourseRepository.save(courseDto);
    }

    @Override
    public Optional<CourseDto> update(CourseDto courseDto) {
        Optional<CourseDto> existingCourseDto = iCourseRepository.getByIdCourse(courseDto.getIdCourse());
        Optional<CourseDto> courseDtoByTitle = iCourseRepository.getByTitle(courseDto.getTitle());

        if (existingCourseDto.isEmpty()) {
            throw new RuntimeException("El Curso no existe");
        } else if (courseDtoByTitle.isPresent() && !courseDtoByTitle.get().getIdCourse().equals(courseDto.getIdCourse())) {
            throw new RuntimeException("El título del curso ya está en uso");
        }

        return Optional.of(iCourseRepository.save(courseDto));
    }

    @Override
    public boolean deleteById(Integer id) {
        Optional<CourseDto> courseDto = iCourseRepository.getByIdCourse(id);

        if (courseDto.isEmpty()) {
            throw new RuntimeException("Curso no encontrado");
        }

        iCourseRepository.deleteById(id);
        return true;
    }

    public List<CourseDto> getCoursesWithProjectCount() {
        return iCourseRepository.getCoursesWithProjectCount();
    }
}
