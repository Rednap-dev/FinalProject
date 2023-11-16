package com.rednap.finalproject.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID;
    private String username;
    private String email;
    private String passHash;
    private UserRole role;


    public enum UserRole {
        ROLE_USER, ROLE_ADMIN
    }
}
