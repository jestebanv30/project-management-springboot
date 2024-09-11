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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user")
    private String idUser;

    private String name;

    private String email;

    private String username;

    private String password;

    private String role;

    @OneToMany(mappedBy = "student", orphanRemoval = true)
    private List<ProjectEntity> projects;
}
