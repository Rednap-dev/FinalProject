package com.rednap.finalproject.model.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ItemCreateRequest {
    @NotBlank
    private String name;
    private String description;
    @Min(value = 1)
    private double price;
}
