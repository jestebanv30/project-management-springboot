package com.juanesdev.project_management.persistance.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
@Entity
@Table(name = "projects")
public class ProjectEntity {

    @Id
    @Column(name = "id_project")
    private String idProject;

    @Column(name = "student_id")
    private String studentId;

    @Column(name = "course_id")
    private String courseId;

    private String title;

    private String description;

    @Column(name = "delivery_date")
    private LocalDateTime deliveryDate;

    private String status;

    @Column(name = "files_url")
    private String filesUrl;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    //relacion con UserEntity (El estudiante que creó el proyecto)
    @ManyToOne
    @JoinColumn(name = "student_id", insertable = false, updatable = false)
    private UserEntity student;

    // Relación con CourseEntity (el curso asociado al proyecto)
    @ManyToOne
    @JoinColumn(name = "course_id", insertable = false, updatable = false)
    private CourseEntity course;
}
