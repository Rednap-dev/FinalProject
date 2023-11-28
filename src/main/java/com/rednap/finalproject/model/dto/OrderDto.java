package com.rednap.finalproject.model.dto;

import java.util.List;

public record OrderDto(List<ItemStackDto> items, double price, Long id) {
}
