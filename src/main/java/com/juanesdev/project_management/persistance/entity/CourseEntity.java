package com.juanesdev.project_management.persistance.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
@Table(name = "courses")
public class CourseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_course")
    private String idCourse;

    @Column(name = "teacher_id")
    private String teacherId;

    private String title;

    @ManyToOne
    @JoinColumn(name = "teacher_id", referencedColumnName = "id_user", nullable = true)
    private UserEntity teacher;
}
