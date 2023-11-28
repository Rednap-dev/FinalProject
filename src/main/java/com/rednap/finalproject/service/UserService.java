package com.rednap.finalproject.service;

import com.rednap.finalproject.model.dto.JwtResponse;
import com.rednap.finalproject.model.dto.UserInfo;
import com.rednap.finalproject.model.dto.UserLoginRequest;
import com.rednap.finalproject.model.dto.UserRegisterRequest;
import com.rednap.finalproject.model.entity.OrderEntity;
import com.rednap.finalproject.model.entity.UserEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface UserService {
    Optional<JwtResponse> authenticate(final UserLoginRequest userLoginRequest);
    Optional<JwtResponse> register(final UserRegisterRequest userRegisterRequest);
    Optional<UserInfo> getUserInfo();
    Optional<UserEntity> getCurrentUser();
    Optional<UserEntity> getById(final Long id);
    void addOrder(final OrderEntity orderEntity);
}
