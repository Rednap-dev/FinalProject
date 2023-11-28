package com.rednap.finalproject.service.impl;

import com.rednap.finalproject.model.dto.ItemStackDto;
import com.rednap.finalproject.model.entity.ItemEntity;
import com.rednap.finalproject.model.entity.ItemStackEntity;
import com.rednap.finalproject.repository.ItemStackRepository;
import com.rednap.finalproject.service.ItemService;
import com.rednap.finalproject.service.ItemStackService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ItemStackServiceImpl implements ItemStackService {

    private final ItemStackRepository itemStackRepository;
    private final ItemService itemService;

    @Override
    public Optional<ItemStackEntity> generateItemStackEntity(final ItemStackDto itemStackDto) {
        final Long itemId = itemStackDto.getItemId();
        Optional<ItemEntity> itemEntity = itemService.getById(itemId);
        if(itemEntity.isEmpty()) {
            return Optional.empty();
        }

        final ItemStackEntity itemStackEntity = new ItemStackEntity();
        itemStackEntity.setItem(itemEntity.get());
        itemStackEntity.setAmount(itemStackDto.getAmount());
        itemStackRepository.save(itemStackEntity);
        return Optional.of(itemStackEntity);
    }
}
