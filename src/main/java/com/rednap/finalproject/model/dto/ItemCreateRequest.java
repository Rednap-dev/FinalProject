package com.rednap.finalproject.model.dto;

import lombok.Data;

@Data
public class ItemCreateRequest {
    private String name;
    private String description;
    private double price;
}
