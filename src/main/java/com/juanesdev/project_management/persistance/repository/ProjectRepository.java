package com.juanesdev.project_management.persistance.repository;

import com.juanesdev.project_management.domain.dto.ProjectDto;
import com.juanesdev.project_management.domain.repository.IProjectRepository;
import com.juanesdev.project_management.persistance.crud.IProjectCrud;
import com.juanesdev.project_management.persistance.entity.ProjectEntity;
import com.juanesdev.project_management.persistance.mapper.IProjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class ProjectRepository implements IProjectRepository {

    private final IProjectMapper iProjectMapper;
    private final IProjectCrud iProjectCrud;

    @Override
    public List<ProjectDto> getAll() {
        return iProjectMapper.toProjectDtoList(iProjectCrud.findAll());
    }

    @Override
    public List<ProjectDto> getByCourseId(String courseId) {
        return iProjectMapper.toProjectDtoList(iProjectCrud.findAllByCourseId(courseId));
    }

    @Override
    public List<ProjectDto> getByStudentId(String studentId) {
        return iProjectMapper.toProjectDtoList(iProjectCrud.findAllByStudentId(studentId));
    }

    @Override
    public ProjectDto save(ProjectDto projectDto) {
        ProjectEntity projectEntity = iProjectMapper.toProjectEntity(projectDto);
        return iProjectMapper.toProjectDto(iProjectCrud.save(projectEntity));
    }

    @Override
    public void deleteById(String id) {
        iProjectCrud.deleteById(id);
    }

    // Metodo para obtener el proyecto mas reciente aprobado
    public Optional<ProjectDto> getMostRecentApprovedProject() {
        return iProjectCrud.findMostRecentApprovedProject().map(iProjectMapper::toProjectDto);
    }
}
