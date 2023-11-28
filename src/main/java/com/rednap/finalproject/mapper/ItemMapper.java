package com.rednap.finalproject.mapper;

import com.rednap.finalproject.model.dto.ItemCreateRequest;
import com.rednap.finalproject.model.entity.ItemEntity;
import org.springframework.stereotype.Component;

@Component
public class ItemMapper {

    public ItemEntity toItemEntity(final ItemCreateRequest itemCreateRequest) {
        final ItemEntity itemEntity = new ItemEntity();
        itemEntity.setName(itemCreateRequest.getName());
        itemEntity.setDescription(itemCreateRequest.getDescription());
        itemEntity.setPrice(itemCreateRequest.getPrice());
        return itemEntity;
    }

}
