package com.juanesdev.project_management.domain.service;

import com.juanesdev.project_management.domain.dto.ProjectDto;
import com.juanesdev.project_management.domain.repository.IProjectRepository;
import com.juanesdev.project_management.domain.usecase.IProjectUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ProjectService implements IProjectUseCase {

    private final IProjectRepository iProjectRepository;

    @Override
    public List<ProjectDto> getAll() {
        return iProjectRepository.getAll();
    }

    @Override
    public List<ProjectDto> getByCourseId(String courseId) {
        List<ProjectDto> projectDto = iProjectRepository.getByCourseId(courseId);

        if (projectDto.isEmpty()) {
            throw new RuntimeException("");
        }
        return List.of();
    }

    @Override
    public List<ProjectDto> getByStudentId(String studentId) {
        return List.of();
    }

    @Override
    public ProjectDto save(ProjectDto projectDto) {
        return null;
    }

    @Override
    public ProjectDto update(ProjectDto projectDto) {
        return null;
    }

    @Override
    public boolean deleteById(String id) {
        return false;
    }
}
