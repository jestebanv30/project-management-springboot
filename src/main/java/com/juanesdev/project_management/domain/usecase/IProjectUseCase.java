package com.juanesdev.project_management.domain.usecase;

import com.juanesdev.project_management.domain.dto.ProjectDto;

import java.util.List;

public interface IProjectUseCase {
    List<ProjectDto> getAll();
    List<ProjectDto> getByCourseId(String courseId);
    List<ProjectDto> getByStudentId(String studentId);
    ProjectDto save(ProjectDto projectDto);
    ProjectDto update(ProjectDto projectDto);
    boolean deleteById(String id);
}
