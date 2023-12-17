package com.rednap.finalproject.controller;

import com.rednap.finalproject.configuration.annotations.IfAdmin;
import com.rednap.finalproject.model.dto.OrderCreateRequest;
import com.rednap.finalproject.model.dto.OrderDto;
import com.rednap.finalproject.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/order")
@CrossOrigin(origins = "*", methods = {RequestMethod.POST, RequestMethod.GET})
@RequiredArgsConstructor
@Slf4j
public class OrderController {

    private final OrderService orderService;

    @GetMapping("/current")
    public ResponseEntity getOrdersForCurrentUser() {
        final List<OrderDto> orders = orderService.getOrdersForCurrentUser();
        if (orders.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        log.info("item method getOrdersForCurrentUser is working!");
        return ResponseEntity.ok(orders);
    }

    @IfAdmin
    @GetMapping("/belongs/{id}")
    public ResponseEntity getOrdersForSpecificUser(@PathVariable Long id) {
        final List<OrderDto> orders = orderService.getOrdersForUser(id);

        if (orders.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(orders);
    }

    @PostMapping("/create")
    public ResponseEntity createOrder(@RequestBody OrderCreateRequest orderCreateRequest) {
        final Optional<OrderDto> orderDto = orderService.createOrder(orderCreateRequest);

        if (orderDto.isEmpty()) {
            return ResponseEntity.accepted().build();
        }

        return ResponseEntity.ok(orderDto.get());
    }

    @GetMapping("/{id}/approve")
    public void approve(@PathVariable Long id, @RequestParam String code) {
        orderService.approveOrder(id, code);
    }

    @GetMapping("/{id}/decline")
    public void decline(@PathVariable Long id, @RequestParam String code) {
        orderService.declineOrder(id, code);
    }

}
