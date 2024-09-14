package com.juanesdev.project_management.persistance.crud;

import com.juanesdev.project_management.persistance.entity.ProjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IProjectCrud extends JpaRepository<ProjectEntity, String> {
    List<ProjectEntity> findAllByCourseId(String courseId);

    List<ProjectEntity> findAllByStudentId(String studentId);
}
