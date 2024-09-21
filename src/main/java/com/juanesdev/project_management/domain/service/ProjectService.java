package com.juanesdev.project_management.domain.service;

import com.juanesdev.project_management.domain.dto.ProjectDto;
import com.juanesdev.project_management.domain.repository.IProjectRepository;
import com.juanesdev.project_management.domain.usecase.IProjectUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
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
    public Optional<ProjectDto> getById(Integer idProject) {
        Optional<ProjectDto> projectDto = iProjectRepository.getById(idProject);

        if (projectDto.isEmpty()) {
            throw new RuntimeException("Proyecto no encontrado");
        }
        return projectDto;
    }

    @Override
    public Optional<ProjectDto> getByTitle(String title) {
        Optional<ProjectDto> projectDto = iProjectRepository.getByTitle(title);

        if (projectDto.isEmpty()) {
            throw new RuntimeException("Proyecto no encontrado");
        }

        return projectDto;
    }

    @Override
    public List<ProjectDto> getByCourseId(Integer courseId) {
        List<ProjectDto> projectDtoListDto = iProjectRepository.getByCourseId(courseId);

        if (projectDtoListDto.isEmpty()) {
            throw new RuntimeException("No hay proyectos asociado a esa materia");
        }
        return projectDtoListDto;
    }

    @Override
    public List<ProjectDto> getByStudentId(String studentId) {
        List<ProjectDto> projectsDtoListByStudent = iProjectRepository.getByStudentId(studentId);

        if (projectsDtoListByStudent.isEmpty()) {
            throw new RuntimeException("No hay proyectos a ese estudiante");
        }
        return projectsDtoListByStudent;
    }

    @Override
    public ProjectDto save(ProjectDto projectDto) {
        Optional<ProjectDto> projectDtoId = iProjectRepository.getById(projectDto.getIdProject());
        Optional<ProjectDto> projectDtoTitle = iProjectRepository.getByTitle(projectDto.getTitle());

        if (projectDtoId.isPresent() || projectDtoTitle.isPresent()) {
            throw new RuntimeException("El proyecto ya existe");
        }

        return iProjectRepository.save(projectDto);
    }

    @Override
    public Optional<ProjectDto> update(ProjectDto projectDto) {

        Optional<ProjectDto> projectDtoId = iProjectRepository.getById(projectDto.getIdProject());
        Optional<ProjectDto> projectDtoTitle = iProjectRepository.getByTitle(projectDto.getTitle());

        if (projectDtoId.isEmpty()) {
            throw new RuntimeException("El proyecto no existe");
        } else if (projectDtoTitle.isPresent() && !projectDtoTitle.get().getIdProject().equals(projectDto.getIdProject())) {
            throw new RuntimeException("El título ya está en uso");
        }

        return Optional.of(iProjectRepository.save(projectDto));
    }

    @Override
    public boolean deleteById(Integer id) {
        Optional<ProjectDto> projectDtoById = iProjectRepository.getById(id);

        if (projectDtoById.isEmpty()) {
            throw new RuntimeException("El proyecto no existe");
        }

        iProjectRepository.deleteById(id);
        return false;
    }
}
