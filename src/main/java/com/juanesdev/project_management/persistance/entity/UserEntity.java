package com.juanesdev.project_management.persistance.entity;

import jakarta.persistence.*;

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
}
