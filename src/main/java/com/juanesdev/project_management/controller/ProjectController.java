package com.juanesdev.project_management.controller;

import com.juanesdev.project_management.domain.dto.ProjectDto;
import com.juanesdev.project_management.domain.dto.ProjectResponseDto;
import com.juanesdev.project_management.domain.enums.ProjectStatus;
import com.juanesdev.project_management.domain.usecase.IProjectUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/project")
public class ProjectController {

    private final IProjectUseCase iProjectUseCase;

    @GetMapping
    public ResponseEntity<List<ProjectResponseDto>> getAll() {
        return ResponseEntity.ok(iProjectUseCase.getAll());
    }

    @GetMapping("/{idProject}")
    public ResponseEntity<ProjectDto> getById(@PathVariable Integer idProject) {
        return ResponseEntity.of(iProjectUseCase.getById(idProject));
    }

    @GetMapping("/title-{title}")
    public ResponseEntity<ProjectDto> getByTitle (@PathVariable String title) {
        return ResponseEntity.of(iProjectUseCase.getByTitle(title));
    }

    @GetMapping("/courseId-{courseId}")
    public ResponseEntity<List<ProjectResponseDto>> getByCourseId (@PathVariable Integer courseId) {
        return ResponseEntity.of(Optional.ofNullable(iProjectUseCase.getByCourseId(courseId)));
    }

    @GetMapping("/studentId-{studentId}")
    public ResponseEntity<List<ProjectDto>> getByStudentId (@PathVariable String studentId) {
        return ResponseEntity.ok(iProjectUseCase.getByStudentId(studentId));
    }

    @PostMapping()
    public ResponseEntity<ProjectDto> save(@RequestBody ProjectDto projectDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(iProjectUseCase.save(projectDto));
    }

    @PutMapping()
    public ResponseEntity<ProjectDto> update(@RequestBody ProjectDto projectDto) {
        return ResponseEntity.of(iProjectUseCase.update(projectDto));
    }

    @DeleteMapping(path = "/{idProject}")
    public ResponseEntity<ProjectDto> delete(@PathVariable Integer idProject) {
        return new ResponseEntity<>(this.iProjectUseCase.deleteById(idProject) ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @GetMapping("/recentProject")
    public ResponseEntity<ProjectResponseDto> findFirstByStatusOrderByCreatedAtDesc(@RequestParam ProjectStatus status) {
        return ResponseEntity.of(iProjectUseCase.findFirstByStatusOrderByCreatedAtDesc(status));
    }

    @GetMapping("/view-project")
    public ResponseEntity<ProjectResponseDto> getByIdProjectViewPost(@RequestParam Integer idProject) {
        return ResponseEntity.of(iProjectUseCase.getByIdProjectViewPost(idProject));
    }
}
