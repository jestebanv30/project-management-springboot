package com.juanesdev.project_management.persistance.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

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

    @Column(name = "total_projects")
    private Integer totalProjects;

    //Relacion con UserEntity (el profesor del curso)
    @ManyToOne
    @JoinColumn(name = "teacher_id", insertable = false, updatable = false)
    private UserEntity teacher;

    //Relacion con ProjectEntity (los proyectos asociados a este curso)
    @OneToMany(mappedBy = "course")
    private List<ProjectEntity> projects;
}
