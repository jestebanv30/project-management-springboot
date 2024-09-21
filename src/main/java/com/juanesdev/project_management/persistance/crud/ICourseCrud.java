package com.juanesdev.project_management.persistance.crud;

import com.juanesdev.project_management.domain.dto.CourseDto;
import com.juanesdev.project_management.persistance.entity.CourseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ICourseCrud extends JpaRepository<CourseEntity, Integer> {

    /**
     * LEFT JOIN c.projects p: Une la entidad CourseEntity con su colección de proyectos (projects).
     * COUNT(p): Cuenta cuántos proyectos tiene cada curso.
     * GROUP BY c: Agrupa los resultados por cada curso.
     * @return Lista de los cursos con el conteo de proyectos asociados
     */
    @Query("SELECT new com.juanesdev.project_management.domain.dto.CourseDto(c.idCourse, c.teacherId, c.title, COUNT(p)) " +
            "FROM CourseEntity c LEFT JOIN c.projects p " +
            "GROUP BY c.idCourse, c.teacherId, c.title")
    List<CourseDto> findCoursesWithProjectCount();
}
