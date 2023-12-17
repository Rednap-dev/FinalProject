package com.rednap.finalproject.service.impl;

import com.rednap.finalproject.generator.CodeGenerator;
import com.rednap.finalproject.mapper.OrderMapper;
import com.rednap.finalproject.model.dto.OrderCreateRequest;
import com.rednap.finalproject.model.dto.OrderDto;
import com.rednap.finalproject.model.entity.CodeEntity;
import com.rednap.finalproject.model.entity.ItemStackEntity;
import com.rednap.finalproject.model.entity.OrderEntity;
import com.rednap.finalproject.repository.OrderRepository;
import com.rednap.finalproject.service.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.weaver.ast.Or;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final UserService userService;
    private final OrderMapper orderMapper;
    private final ItemStackService itemStackService;
    private final MailService mailService;
    private final CodeService codeService;

    @Override
    public Optional<OrderDto> createOrder(final OrderCreateRequest orderCreateRequest) {
        final List<ItemStackEntity> itemStackEntities = orderCreateRequest.getItems().stream()
                .map(itemStackService::generateItemStackEntity)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());

        if(itemStackEntities.isEmpty()) {
            log.warn("ItemStack is empty");
            return Optional.empty();
        }

        final OrderEntity orderEntity = new OrderEntity();
        orderEntity.setOrderStatus(OrderEntity.OrderStatus.WAITING_FOR_APPROVAL);
        orderEntity.setItems(itemStackEntities);
        orderRepository.save(orderEntity);
        userService.addOrder(orderEntity);

        final String code = codeService.generateCode(orderEntity);

        mailService.sendOrderApprovalCode(code, orderEntity);

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
    public void approveOrder(final Long orderId, final String code) {
        if(checkCode(orderId, code)) {
            final OrderEntity orderEntity = orderRepository.findById(orderId).get();
            orderEntity.setOrderStatus(OrderEntity.OrderStatus.APPROVED);
            orderRepository.save(orderEntity);
        }
    }

    @Override
    public void declineOrder(final Long orderId, final String code) {
        if(checkCode(orderId, code)) {
            final OrderEntity orderEntity = orderRepository.findById(orderId).get();
            userService.removeOrder(orderEntity);
            orderRepository.delete(orderEntity);
        }
    }

    private boolean checkCode(final Long orderId, final String code) {
        if(!orderRepository.existsById(orderId)) {
            return false;
        }

        return codeService.validate(code, orderId);
    }
}
