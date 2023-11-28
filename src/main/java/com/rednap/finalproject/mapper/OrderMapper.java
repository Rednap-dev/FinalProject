package com.rednap.finalproject.mapper;

import com.rednap.finalproject.model.dto.ItemStackDto;
import com.rednap.finalproject.model.dto.OrderDto;
import com.rednap.finalproject.model.entity.ItemStackEntity;
import com.rednap.finalproject.model.entity.OrderEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class OrderMapper {

    public OrderDto toDto(final OrderEntity orderEntity) {
        return new OrderDto(orderEntity.getItems()
                    .stream()
                    .map(this::convertToItemStackDto)
                    .collect(Collectors.toList()),
                orderEntity
                        .getItems()
                        .stream()
                        .mapToDouble(itemStack -> itemStack.getAmount() * itemStack.getItem().getPrice())
                        .sum(),
                orderEntity.getId());
    }

    private ItemStackDto convertToItemStackDto(final ItemStackEntity itemStackEntity) {
        return new ItemStackDto(itemStackEntity.getItem().getId(), itemStackEntity.getAmount());
    }

}
