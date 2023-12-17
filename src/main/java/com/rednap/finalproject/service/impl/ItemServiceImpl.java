package com.rednap.finalproject.service.impl;

import com.rednap.finalproject.exception.ItemNameConflictException;
import com.rednap.finalproject.mapper.ItemMapper;
import com.rednap.finalproject.model.dto.ItemCreateRequest;
import com.rednap.finalproject.model.entity.ItemEntity;
import com.rednap.finalproject.repository.ItemRepository;
import com.rednap.finalproject.service.ItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;
    private final ItemMapper itemMapper;

    @Override
    public List<ItemEntity> getAllItems() {
        return itemRepository.findAll();
    }

    @Override
    public List<ItemEntity> searchByNameLike(final String searchQuery) {
        return itemRepository.findAllByNameLike(searchQuery);
    }

    @Override
    public Optional<ItemEntity> getById(final Long id) {
        return itemRepository.findById(id);
    }

    @Override
    public ItemEntity addItem(final ItemCreateRequest itemCreateRequest) {
        if(itemRepository.existsByName(itemCreateRequest.getName())) {
            log.warn("Item with this name already exists. Name: " + itemCreateRequest.getName());
            throw new ItemNameConflictException(itemCreateRequest.getName());
        }

        final ItemEntity itemEntity = itemMapper.toItemEntity(itemCreateRequest);
        itemRepository.save(itemEntity);
        return itemEntity;
    }
}
