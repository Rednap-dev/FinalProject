package com.rednap.finalproject.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
public class MailBeansConfig {
    @Value("${netshop.mail.host}")
    private String emailHost;
    @Value("${netshop.mail.username}")
    private String emailUsername;
    @Value("${netshop.mail.password}")
    private String emailPassword;
    @Value("${netshop.mail.port}")
    private int emailPort;
    @Value("${netshop.mail.protocol}")
    private String emailProtocol;

    @Bean
    public JavaMailSender getJavaMailSender() {
        final JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(emailHost);
        mailSender.setPort(emailPort);

        mailSender.setUsername(emailUsername);
        mailSender.setPassword(emailPassword);

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", emailProtocol);
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        return mailSender;
    }
}
