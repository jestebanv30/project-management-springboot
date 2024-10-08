package com.juanesdev.project_management.domain.repository;

import com.juanesdev.project_management.domain.dto.ProjectDto;
import com.juanesdev.project_management.domain.enums.ProjectStatus;

import java.util.List;
import java.util.Optional;

public interface IProjectRepository {
    List<ProjectDto> getAll();

    Optional<ProjectDto> getById(Integer idProject);

    Optional<ProjectDto> getByTitle(String title);

    List<ProjectDto> getByCourseId(Integer courseId);

    List<ProjectDto> getByStudentId(String studentId);

    ProjectDto save(ProjectDto projectDto);

    void deleteById(Integer id);

    Optional<ProjectDto> findFirstByStatusOrderByCreatedAtDesc(ProjectStatus status);

    Long countByStudentIdAndStatus(String studentId, ProjectStatus statusString);
}
