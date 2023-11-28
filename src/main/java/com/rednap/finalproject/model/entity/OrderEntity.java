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
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
    private List<ItemStackEntity> items = new ArrayList<>();
    private OrderStatus orderStatus;

    public void addItemStack(final ItemStackEntity itemStackEntity) {
        items.add(itemStackEntity);
    }

    public enum OrderStatus {
        WAITING_FOR_APPROVAL, APPROVED
    }
}
