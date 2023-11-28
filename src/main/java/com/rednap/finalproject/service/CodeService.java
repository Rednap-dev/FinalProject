package com.rednap.finalproject.service;

import com.rednap.finalproject.model.entity.OrderEntity;
import org.springframework.stereotype.Service;

@Service
public interface CodeService {
    boolean validate(final String code, final Long orderId);
    String generateCode(final OrderEntity orderEntity);
}
