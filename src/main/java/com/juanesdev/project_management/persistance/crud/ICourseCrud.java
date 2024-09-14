package com.juanesdev.project_management.persistance.crud;

import com.juanesdev.project_management.persistance.entity.CourseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ICourseCrud extends JpaRepository<CourseEntity, Integer> {
    Optional<CourseEntity> findByTitle(String title);
}
