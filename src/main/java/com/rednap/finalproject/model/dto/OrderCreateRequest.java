package com.rednap.finalproject.model.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class OrderCreateRequest {
    private List<ItemStackDto> items = new ArrayList<>();
}
