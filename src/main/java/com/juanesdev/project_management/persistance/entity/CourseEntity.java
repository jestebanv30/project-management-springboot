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
    private Integer idCourse;

    private String title;

    @Column(name = "teacher_id")
    private String teacherId;

    @ManyToOne
    @JoinColumn(name = "teacher_id", insertable = false, updatable = false)
    private UserEntity teacher;
}
