package com.rednap.finalproject.model.dto;

import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemStackDto {
    @Min(value = 0)
    private Long itemId;
    @Min(value = 1)
    private int amount;
}
