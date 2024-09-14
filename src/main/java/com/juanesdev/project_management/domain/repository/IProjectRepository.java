package com.juanesdev.project_management.domain.repository;

import com.juanesdev.project_management.domain.dto.ProjectDto;

import java.util.List;

public interface IProjectRepository {
    List<ProjectDto> getAll();
    List<ProjectDto> getByCourseId(String courseId);
    List<ProjectDto> getByStudentId(String studentId);
    ProjectDto save(ProjectDto projectDto);
    void deleteById(String id);
}
