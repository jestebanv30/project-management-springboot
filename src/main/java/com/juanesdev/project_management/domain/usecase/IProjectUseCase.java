package com.juanesdev.project_management.domain.usecase;

import com.juanesdev.project_management.domain.dto.ProjectDto;
import com.juanesdev.project_management.domain.dto.ProjectResponseDto;
import com.juanesdev.project_management.domain.enums.ProjectStatus;

import java.util.List;
import java.util.Optional;

public interface IProjectUseCase {
    List<ProjectResponseDto> getAll();

    Optional<ProjectDto> getById(Integer idProject);

    Optional<ProjectDto> getByTitle(String title);

    List<ProjectResponseDto> getByCourseId(Integer courseId);

    List<ProjectDto> getByStudentId(String studentId);

    ProjectDto save(ProjectDto projectDto);

    Optional<ProjectDto> update(ProjectDto projectDto);

    boolean deleteById(Integer id);

    Optional<ProjectResponseDto> findFirstByStatusOrderByCreatedAtDesc(ProjectStatus status);
}
