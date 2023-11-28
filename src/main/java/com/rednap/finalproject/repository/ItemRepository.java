package com.rednap.finalproject.repository;

import com.rednap.finalproject.model.entity.ItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<ItemEntity, Long> {
    List<ItemEntity> findAllByNameLike(final String name);
    boolean existsByName(final String name);
}
