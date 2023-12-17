package com.rednap.finalproject.test.service;

import com.rednap.finalproject.mapper.UserMapper;
import com.rednap.finalproject.model.entity.UserEntity;
import com.rednap.finalproject.repository.UserRepository;
import com.rednap.finalproject.security.JwtUtils;
import com.rednap.finalproject.service.*;
import com.rednap.finalproject.service.impl.UserServiceImpl;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@TestConfiguration
@PropertySource("classpath:application.properties")
public class ServiceTestConfig {

    @Bean
    public UserService userService() {
        return new UserServiceImpl(userRepository(), passwordEncoder(), jwtUtils(), userMapper());
    }

    @Bean
    public UserRepository userRepository() {
        final UserRepository userRepository = mock(UserRepository.class);
        when(userRepository.findByUsername("auth_error")).thenReturn(Optional.empty());
        when(userRepository.findByUsername("auth_success")).thenReturn(Optional.of(new UserEntity()));
        return userRepository;
    }

    @Bean
    public JwtUtils jwtUtils() {
        final JwtUtils jwtUtils = mock(JwtUtils.class);
        when(jwtUtils.generate(any())).thenReturn("success");
        return jwtUtils;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        final PasswordEncoder passwordEncoder = mock(PasswordEncoder.class);
        when(passwordEncoder.matches(any(), any())).thenReturn(true);
        return passwordEncoder;
    }

    @Bean
    public UserMapper userMapper() {
        return new UserMapper();
    }

}