package com.rednap.finalproject.test.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rednap.finalproject.model.dto.JwtResponse;
import com.rednap.finalproject.model.dto.UserInfo;
import com.rednap.finalproject.model.dto.UserLoginRequest;
import com.rednap.finalproject.model.dto.UserRegisterRequest;
import com.rednap.finalproject.security.JwtUtils;
import com.rednap.finalproject.service.UserService;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import java.util.Optional;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@TestConfiguration
public class ControllerTestConfig {

    @Bean
    public UserService userService() {
        final UserService userService = mock(UserService.class);

        final UserRegisterRequest successUserRegisterRequest = new UserRegisterRequest();
        successUserRegisterRequest.setEmail("test1");
        successUserRegisterRequest.setUsername("test1");
        successUserRegisterRequest.setPassword("test1");
        when(userService.register(successUserRegisterRequest)).thenReturn(Optional.of(new JwtResponse("success")));

        final UserRegisterRequest emptyUserRegisterRequest = new UserRegisterRequest();
        emptyUserRegisterRequest.setEmail("test2");
        emptyUserRegisterRequest.setUsername("test2");
        emptyUserRegisterRequest.setPassword("test2");
        when(userService.register(emptyUserRegisterRequest)).thenReturn(Optional.empty());

        final UserLoginRequest succesUserLoginRequest = new UserLoginRequest();
        succesUserLoginRequest.setUsername("test1");
        succesUserLoginRequest.setPassword("test1");
        when(userService.authenticate(succesUserLoginRequest)).thenReturn(Optional.of(new JwtResponse("success")));

        final UserLoginRequest emptyUserLoginRequest = new UserLoginRequest();
        emptyUserLoginRequest.setUsername("test2");
        emptyUserLoginRequest.setPassword("test2");
        when(userService.authenticate(emptyUserLoginRequest)).thenReturn(Optional.empty());

        final UserInfo succesUserInfo = new UserInfo(1l, "test1", "testMail@gmail.com", "User");
        when(userService.getUserInfo()).thenReturn(Optional.of(succesUserInfo));
        return userService;
    }

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }

    @Bean
    public JwtUtils jwtUtils() {
        return new JwtUtils();
    }

}