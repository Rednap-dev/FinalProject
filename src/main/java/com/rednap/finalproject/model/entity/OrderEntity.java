package com.rednap.finalproject.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GenerationType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.OneToMany;
import jakarta.persistence.FetchType;
import jakarta.persistence.CascadeType;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Data
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
    private List<ItemStackEntity> items = new ArrayList<>();
    private OrderStatus orderStatus;

    @Override
    public String toString() {
        return items.stream().map(ItemStackEntity::toString).collect(Collectors.joining("<br>"));
    }

    public enum OrderStatus {
        WAITING_FOR_APPROVAL, APPROVED
    }
}
