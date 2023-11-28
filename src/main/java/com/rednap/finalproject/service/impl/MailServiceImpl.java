package com.rednap.finalproject.service.impl;

import com.rednap.finalproject.model.entity.OrderEntity;
import com.rednap.finalproject.service.MailService;
import com.rednap.finalproject.service.UserService;
import jakarta.mail.Multipart;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MailServiceImpl implements MailService {

    private final JavaMailSender javaMailSender;
    private final UserService userService;

    @Value("${netshop.mail.username}")
    private String from;

    @Override
    @SneakyThrows
    public void sendOrderApprovalCode(final String code, final OrderEntity orderEntity) {
        final String to = userService.getCurrentUser().get().getEmail();
        final Long orderId = orderEntity.getId();

        final StringBuilder buffer = new StringBuilder();
        buffer.append(String.format("Hello %s!<br><br>", to));
        buffer.append("Here is your order:<br>");
        buffer.append(orderEntity.toString() + "<br><br>");
        buffer.append(String.format("Click on the link below to approve your order<br>"));
        buffer.append(String.format("http://localhost:8080/api/order/%d/appprove?code=%s<br><br>", orderId, code));
        buffer.append(String.format("In case you didn't create this order, please click on the link below to decline it<br>"));
        buffer.append(String.format("http://localhost:8080/api/order/%d/decline?code=%s", orderId, code));

        final MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        final MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "UTF-8");

        helper.setText(buffer.toString(), true);
        helper.setTo(to);
        helper.setSubject(String.format("Approve order [#%d]", orderId));
        helper.setFrom(from);
        javaMailSender.send(mimeMessage);
    }
}
