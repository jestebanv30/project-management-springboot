package com.juanesdev.project_management.persistance.mapper;

import com.juanesdev.project_management.domain.dto.CourseDto;
import com.juanesdev.project_management.persistance.entity.CourseEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ICourseMapper {
    CourseDto toCourseDto(CourseEntity courseEntity);

    CourseEntity toCourseEntity(CourseDto courseDto);

    List<CourseDto> toCourseDtoList(List<CourseEntity> courseEntityList);
}
