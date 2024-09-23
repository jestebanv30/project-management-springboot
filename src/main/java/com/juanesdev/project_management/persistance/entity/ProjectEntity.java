package com.juanesdev.project_management.persistance.entity;

import com.juanesdev.project_management.domain.enums.ProjectStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Getter @Setter
@Entity
@Table(name = "projects")
public class ProjectEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_project")
    private Integer idProject;

    @Column(name = "student_id")
    private String studentId;

    @Column(name = "course_id")
    private Integer courseId;

    private String title;

    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private ProjectStatus status;

    @Column(name = "files_url")
    private String filesUrl;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
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
