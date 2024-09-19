package com.juanesdev.project_management.persistance.crud;

import com.juanesdev.project_management.persistance.entity.CourseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ICourseCrud extends JpaRepository<CourseEntity, Integer> {

    @Query("SELECT c FROM CourseEntity c WHERE LOWER(c.title) LIKE LOWER(CONCAT('%', :title, '%'))")
    Optional<CourseEntity> findByTitleIgnoreCase(String title);
}
