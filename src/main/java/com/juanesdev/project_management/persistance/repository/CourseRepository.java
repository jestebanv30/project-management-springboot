package com.juanesdev.project_management.persistance.repository;

import com.juanesdev.project_management.domain.dto.CourseDto;
import com.juanesdev.project_management.domain.repository.ICourseRepository;
import com.juanesdev.project_management.persistance.crud.ICourseCrud;
import com.juanesdev.project_management.persistance.entity.CourseEntity;
import com.juanesdev.project_management.persistance.mapper.ICourseMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class CourseRepository implements ICourseRepository {

    private final ICourseCrud iCourseCrud;
    private final ICourseMapper iCourseMapper;

    @Override
    public List<CourseDto> getAll() {
        return iCourseMapper.toCourseDtoList(iCourseCrud.findAll());
    }

    @Override
    public Optional<CourseDto> getByIdCourse(Integer id) {
        return iCourseCrud.findById(id).map(iCourseMapper::toCourseDto);
    }

    @Override
    public Optional<CourseDto> getByTitle(String title) {
        return iCourseCrud.findByTitleIgnoreCase(title).map(iCourseMapper::toCourseDto);
    }

    @Override
    public CourseDto save(CourseDto courseDto) {
        CourseEntity courseEntity = iCourseMapper.toCourseEntity(courseDto);
        return iCourseMapper.toCourseDto(iCourseCrud.save(courseEntity));
    }

    @Override
    public void deleteById(Integer id) {
        iCourseCrud.deleteById(id);
    }

    // Metodo para obtener cursos con el numero de proyectos
    @Override
    public List<CourseDto> getCoursesWithProjectCount() {
        return iCourseCrud.findCoursesWithProjectCount();
    }

}
