package com.juanesdev.project_management.domain.service;

import com.juanesdev.project_management.domain.dto.CourseDto;
import com.juanesdev.project_management.domain.dto.ProjectDto;
import com.juanesdev.project_management.domain.dto.ProjectResponseDto;
import com.juanesdev.project_management.domain.dto.UserDto;
import com.juanesdev.project_management.domain.enums.ProjectStatus;
import com.juanesdev.project_management.domain.repository.ICourseRepository;
import com.juanesdev.project_management.domain.repository.IProjectRepository;
import com.juanesdev.project_management.domain.repository.IUserRepository;
import com.juanesdev.project_management.domain.usecase.IProjectUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ProjectService implements IProjectUseCase {

    private final IProjectRepository iProjectRepository;
    private final IUserRepository iUserRepository;
    private final ICourseRepository iCourseRepository;

    @Override
    public List<ProjectResponseDto> getAll() {
        List<ProjectResponseDto> projectResponseDtoList = new ArrayList<>();
        List<ProjectDto> projectDtoList = iProjectRepository.getAll();

        for (ProjectDto projectDto : projectDtoList) {
            ProjectResponseDto projectResponseDto;
            projectResponseDto = mapToProjectResponseDto(projectDto);
            projectResponseDtoList.add(projectResponseDto);
        }
        return projectResponseDtoList;
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
    public List<ProjectResponseDto> getByCourseId(Integer courseId) {
        List<ProjectDto> projectDtoListDto = iProjectRepository.getByCourseId(courseId);
        List<ProjectResponseDto> projectResponseDtoList = new ArrayList<>();

        if (projectDtoListDto.isEmpty()) {
            throw new RuntimeException("No hay proyectos asociado a esa materia");
        }

        for (ProjectDto projectDto : projectDtoListDto) {
            ProjectResponseDto projectResponseDto;
            projectResponseDto = mapToProjectResponseDto(projectDto);
            projectResponseDtoList.add(projectResponseDto);
        }
        return projectResponseDtoList;
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
        Optional<ProjectDto> projectDtoTitle = iProjectRepository.getByTitle(projectDto.getTitle());

        if (projectDtoTitle.isPresent()) {
            throw new RuntimeException("El proyecto ya existe");
        }

        projectDto.setStatus(ProjectStatus.PENDING);

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

        if (iProjectRepository.getById(id).isEmpty()) {
            throw new RuntimeException("El proyecto no existe");
        }

        iProjectRepository.deleteById(id);
        return true;
    }

    @Override
    public Optional<ProjectResponseDto> findFirstByStatusOrderByCreatedAtDesc(ProjectStatus status) {

        Optional<ProjectDto> projectDto = iProjectRepository.findFirstByStatusOrderByCreatedAtDesc(status);

        if (projectDto.isEmpty()) {
            throw new RuntimeException("No hay proyectos aprovados");
        }
        return Optional.of(mapToProjectResponseDto(projectDto.get()));
    }

    @Override
    public Optional<ProjectResponseDto> getByIdProjectViewPost(Integer idProject) {
        Optional<ProjectDto> projectDto = iProjectRepository.getById(idProject);
        Optional<ProjectResponseDto> projectResponseDto;

        if (projectDto.isEmpty()) {
            throw new RuntimeException("Proyecto no encontrado");
        }

        projectResponseDto = Optional.of(mapToProjectResponseDto(projectDto.get()));

        return projectResponseDto;
    }

    private ProjectResponseDto mapToProjectResponseDto(ProjectDto projectDto) {
        ProjectResponseDto responseDto = new ProjectResponseDto();

        // Asignamos los valores directos
        responseDto.setId(projectDto.getIdProject());
        responseDto.setTitle(projectDto.getTitle());
        responseDto.setDescription(projectDto.getDescription());
        responseDto.setStatus(projectDto.getStatus());

        // Mapear el nombre del estudiante desde algún servicio de usuario
        Optional<UserDto> student = iUserRepository.getByIdUser(projectDto.getStudentId());
        responseDto.setStudentName(student.get().getName());

        // Mapear el nombre del curso desde algún servicio de curso
        Optional<CourseDto> course = iCourseRepository.getByIdCourse(projectDto.getCourseId());
        responseDto.setCourseName(course.get().getTitle());

        // Calcular el tiempo transcurrido
        responseDto.setTimeAgo(calculateTimeAgo(projectDto.getCreatedAt()));

        // Supongamos que las imágenes están almacenadas como URLs en la propiedad filesUrl, las convertimos en una lista
        responseDto.setImages(Arrays.asList(projectDto.getFilesUrl().split(",")));

        return responseDto;
    }

    private String calculateTimeAgo(LocalDateTime createdAt) {
        Duration duration = Duration.between(createdAt, LocalDateTime.now());

        if (duration.toDays() > 0) {
            return duration.toDays() + " days ago";
        } else if (duration.toHours() > 0) {
            return duration.toHours() + " hours ago";
        } else if (duration.toMinutes() > 0) {
            return duration.toMinutes() + " minutes ago";
        } else {
            return "just now";
        }
    }
}
