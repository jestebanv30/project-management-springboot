package com.juanesdev.project_management.persistance.crud;

import com.juanesdev.project_management.persistance.entity.ProjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface IProjectCrud extends JpaRepository<ProjectEntity, Integer> {

    // Obtiene el proyecto más reciente que esté aprobado
    @Query("SELECT p FROM ProjectEntity p WHERE p.status = 'APPROVED' ORDER BY p.createdAt DESC")
    Optional<ProjectEntity> findMostRecentApprovedProject();

    List<ProjectEntity> findAllByCourseId(Integer courseId);

    List<ProjectEntity> findAllByStudentId(String studentId);

    Optional<ProjectEntity> findByTitleIgnoreCase(String title);
}
