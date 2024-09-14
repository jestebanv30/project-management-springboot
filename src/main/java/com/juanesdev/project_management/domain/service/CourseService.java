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
            System.out.println("El usuario no existe");
        }

        return courseDto;
    }

    @Override
    public Optional<CourseDto> getByTitle(String title) {
        Optional<CourseDto> courseDto = iCourseRepository.getByTitle(title);

        if (courseDto.isEmpty()) {
            System.out.println("El usuario no existe");
        }

        return courseDto;
    }

    @Override
    public CourseDto save(CourseDto courseDto) {
        return iCourseRepository.save(courseDto);
    }

    @Override
    public Optional<CourseDto> update(CourseDto courseDto) {
        Optional<CourseDto> existingCourseDto = iCourseRepository.getByIdCourse(courseDto.getIdCourse());

        if (existingCourseDto.isEmpty()) {
            System.out.println("El usuario no existe");
        }

        Optional<CourseDto> existingTitle = iCourseRepository.getByTitle(courseDto.getTitle());

        if (existingTitle.isPresent() && !existingTitle.get().getIdCourse().equals(courseDto.getIdCourse())) {
            System.out.println("El titulo ya está asociado a una cuenta");
        }

        return Optional.of(iCourseRepository.save(courseDto));
    }

    @Override
    public boolean deleteById(Integer id) {
        Optional<CourseDto> courseDto = iCourseRepository.getByIdCourse(id);

        if (courseDto.isEmpty()) {
            System.out.println("El usuario no existe");
        }

        iCourseRepository.deleteById(id);
        return true;
    }
}
