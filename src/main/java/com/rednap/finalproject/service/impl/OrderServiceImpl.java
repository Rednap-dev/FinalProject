package com.rednap.finalproject.service.impl;

import com.rednap.finalproject.mapper.OrderMapper;
import com.rednap.finalproject.model.dto.OrderCreateRequest;
import com.rednap.finalproject.model.dto.OrderDto;
import com.rednap.finalproject.model.entity.ItemStackEntity;
import com.rednap.finalproject.model.entity.OrderEntity;
import com.rednap.finalproject.repository.OrderRepository;
import com.rednap.finalproject.service.ItemStackService;
import com.rednap.finalproject.service.OrderService;
import com.rednap.finalproject.service.UserService;
import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.ast.Or;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final UserService userService;
    private final OrderMapper orderMapper;
    private final ItemStackService itemStackService;

    @Override
    public Optional<OrderDto> createOrder(final OrderCreateRequest orderCreateRequest) {
        final List<ItemStackEntity> itemStackEntities = orderCreateRequest.getItems().stream()
                .map(itemStackService::generateItemStackEntity)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());

        if(itemStackEntities.isEmpty()) {
            return Optional.empty();
        }

        final OrderEntity orderEntity = new OrderEntity();
        orderEntity.setOrderStatus(OrderEntity.OrderStatus.WAITING_FOR_APPROVAL);
        orderEntity.setItems(itemStackEntities);
        orderRepository.save(orderEntity);
        userService.addOrder(orderEntity);
        return Optional.of(orderMapper.toDto(orderEntity));
    }

    @Override
    public List<OrderDto> getOrdersForCurrentUser() {
        return userService.getCurrentUser().get().getOrders()
                .stream()
                .map(orderMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<OrderDto> getOrdersForUser(final Long id) {
        return userService.getById(id).get().getOrders()
                .stream()
                .map(orderMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public boolean approveOrder(Long orderId, int code) {
        return false;
    }

    @Override
    public void declineOrder(final Long orderId) {

    }
}
