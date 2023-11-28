package com.rednap.finalproject.repository;

import com.rednap.finalproject.model.entity.ItemStackEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemStackRepository extends JpaRepository<ItemStackEntity, Long> {
}
