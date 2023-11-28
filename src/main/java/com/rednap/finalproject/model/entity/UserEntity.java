package com.rednap.finalproject.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID;
    private String username;
    private String email;
    private String passHash;
    private UserRole role;
    @OneToMany(fetch = FetchType.LAZY)
    private List<OrderEntity> orders = new ArrayList<>();


    public enum UserRole {
        ROLE_USER, ROLE_ADMIN
    }
}
