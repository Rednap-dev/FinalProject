package com.rednap.finalproject.model.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class CodeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String code;
    @OneToOne
    private OrderEntity orderEntity;
}
