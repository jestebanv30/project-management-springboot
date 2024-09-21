package com.juanesdev.project_management.controller;

import com.juanesdev.project_management.domain.dto.ProjectDto;
import com.juanesdev.project_management.domain.usecase.IProjectUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/project")
public class ProjectController {

    private final IProjectUseCase iProjectUseCase;

    @GetMapping
    public ResponseEntity<List<ProjectDto>> getAll() {
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
    public ResponseEntity<List<ProjectDto>> getByCourseId (@PathVariable Integer courseId) {
        return ResponseEntity.ok(iProjectUseCase.getByCourseId(courseId));
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
}
