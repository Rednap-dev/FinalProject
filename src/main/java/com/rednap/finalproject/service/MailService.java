package com.rednap.finalproject.service;

import com.rednap.finalproject.model.entity.OrderEntity;
import org.springframework.stereotype.Service;

@Service
public interface MailService {

    void sendOrderApprovalCode(final String code, final OrderEntity orderEntity);

}
