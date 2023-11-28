package com.rednap.finalproject.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ItemStackEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private ItemEntity item;
    private int amount;

    @Override
    public String toString() {
        return String.format("%s -> %d", item.toString(), amount);
    }
}
