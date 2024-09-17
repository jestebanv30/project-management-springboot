package com.juanesdev.project_management.persistance.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
@Entity
@Table(name = "users")
public class UserEntity {

    @Id
    @Column(name = "id_user")
    private String idUser;

    private String name;

    private String email;

    private String username;

    private String password;

    private String role;

    // Relación con ProjectEntity (los proyectos creados por el estudiante)
    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProjectEntity> projects;

    //Relación con CourseEntity (los cursos que dicta el usuario si es profesor)
    @OneToMany(mappedBy = "teacher", cascade = CascadeType.ALL)
    private List<CourseEntity> courses;
}
