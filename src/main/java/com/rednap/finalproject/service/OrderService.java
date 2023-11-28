package com.rednap.finalproject.service;

import com.rednap.finalproject.model.dto.OrderCreateRequest;
import com.rednap.finalproject.model.dto.OrderDto;
import com.rednap.finalproject.model.entity.OrderEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface OrderService {
    Optional<OrderDto> createOrder(final OrderCreateRequest orderCreateRequest);
    List<OrderDto> getOrdersForCurrentUser();
    List<OrderDto> getOrdersForUser(final Long id);
    boolean approveOrder(final Long orderId, final int code);
    void declineOrder(final Long orderId);
}
