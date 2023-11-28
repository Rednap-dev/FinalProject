package com.rednap.finalproject.service.impl;

import com.rednap.finalproject.model.entity.ItemEntity;
import com.rednap.finalproject.repository.ItemRepository;
import com.rednap.finalproject.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;

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
}
