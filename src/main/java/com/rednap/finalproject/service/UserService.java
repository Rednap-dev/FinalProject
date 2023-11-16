package com.rednap.finalproject.service;

import com.rednap.finalproject.model.dto.JwtResponse;
import com.rednap.finalproject.model.dto.UserInfo;
import com.rednap.finalproject.model.dto.UserLoginRequest;
import com.rednap.finalproject.model.dto.UserRegisterRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface UserService {
    Optional<JwtResponse> authenticate(final UserLoginRequest userLoginRequest);
    Optional<JwtResponse> register(final UserRegisterRequest userRegisterRequest);
    Optional<UserInfo> getUserInfo();
}
