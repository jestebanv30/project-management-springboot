package com.juanesdev.project_management.controller;

import com.juanesdev.project_management.domain.dto.CourseDto;
import com.juanesdev.project_management.domain.usecase.ICourseUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/course")
public class CourseController {

    private final ICourseUseCase iCourseUseCase;

    @GetMapping
    public ResponseEntity<List<CourseDto>> getAll() {
        return ResponseEntity.ok().body(iCourseUseCase.getAll());
    }

    @GetMapping(path = "/{idCourse}")
    public ResponseEntity<CourseDto> getByIdCourse(@PathVariable Integer idCourse) {
        return ResponseEntity.of(iCourseUseCase.getByIdCourse(idCourse));
    }

    @GetMapping("/with-projects")
    public ResponseEntity<List<CourseDto>> getCoursesWithProjectCount() {
        return ResponseEntity.ok().body(iCourseUseCase.getCoursesWithProjectCount());
    }

    @PostMapping()
    public ResponseEntity<CourseDto> save(@RequestBody CourseDto courseDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(iCourseUseCase.save(courseDto));
    }

    @PutMapping()
    public ResponseEntity<CourseDto> update(@RequestBody CourseDto courseDto) {
        return ResponseEntity.of(iCourseUseCase.update(courseDto));
    }

    @DeleteMapping(path = "/{idCourse}")
    public ResponseEntity<CourseDto> delete(@PathVariable Integer idCourse) {
        return new ResponseEntity<>(this.iCourseUseCase.deleteById(idCourse) ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }
}
