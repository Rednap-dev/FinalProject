package com.rednap.finalproject.model.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToMany
    private List<ItemStackEntity> items = new ArrayList<>();

    public void addItemStack(final ItemStackEntity itemStackEntity) {
        items.add(itemStackEntity);
    }
}
