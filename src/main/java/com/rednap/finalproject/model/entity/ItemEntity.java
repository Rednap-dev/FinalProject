package com.rednap.finalproject.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class ItemEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private double price;
    private String description;

    @Override
    public String toString() {
        final String desc = description != null && description.trim().length() > 0 ? "(" + description + ")" : "";
        return String.format("%s %s - %f$", name, desc, price);
    }
}
