package com.juanesdev.project_management.persistance.mapper;

import com.juanesdev.project_management.domain.dto.ProjectDto;
import com.juanesdev.project_management.persistance.entity.ProjectEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IProjectMapper {
    ProjectDto toProjectDto(ProjectEntity projectEntity);

    ProjectEntity toProjectEntity(ProjectDto projectDto);

    List<ProjectDto> toProjectDtoList(List<ProjectEntity> projectEntities);
}
