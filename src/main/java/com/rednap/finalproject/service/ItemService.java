package com.rednap.finalproject.service;

import com.rednap.finalproject.model.entity.ItemEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface ItemService {
    List<ItemEntity> getAllItems();
    List<ItemEntity> searchByNameLike(final String searchQuery);
    Optional<ItemEntity> getById(final Long id);
}
