package com.rednap.finalproject.controller;

import com.rednap.finalproject.configuration.annotations.IfAdmin;
import com.rednap.finalproject.model.dto.ItemCreateRequest;
import com.rednap.finalproject.model.entity.ItemEntity;
import com.rednap.finalproject.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST})
@RequiredArgsConstructor
@RequestMapping("/api/item")
public class ItemController {

    private final ItemService itemService;

    @GetMapping("/all")
    public ResponseEntity getAll() {
        final List<ItemEntity> items = itemService.getAllItems();
        if (items.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(items);
    }

    @GetMapping("/search")
    public ResponseEntity search(@RequestParam String searchString) {
        final List<ItemEntity> items = itemService.searchByNameLike(searchString);
        if (items.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(items);
    }

    @GetMapping("/{id}")
    public ResponseEntity getById(@PathVariable Long id) {
        final Optional<ItemEntity> itemEntity = itemService.getById(id);
        if (itemEntity.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(itemEntity.get());
    }

    @IfAdmin
    @PostMapping("/add")
    public ItemEntity add(@RequestBody ItemCreateRequest itemCreateRequest) {
        return itemService.addItem(itemCreateRequest);
    }

}
