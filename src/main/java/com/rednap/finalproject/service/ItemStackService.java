package com.rednap.finalproject.service;

import com.rednap.finalproject.model.dto.ItemStackDto;
import com.rednap.finalproject.model.entity.ItemStackEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface ItemStackService {
    Optional<ItemStackEntity> generateItemStackEntity(final ItemStackDto itemStackDto);
}
