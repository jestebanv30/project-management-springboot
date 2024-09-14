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

    @ManyToOne
    @JoinColumn(name = "student_id", insertable = false, updatable = false)
    private UserEntity student;

    @ManyToOne
    @JoinColumn(name = "course_id", insertable = false, updatable = false)
    private CourseEntity course;
}
