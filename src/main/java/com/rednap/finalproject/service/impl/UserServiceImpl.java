package com.rednap.finalproject.service.impl;

import com.rednap.finalproject.mapper.UserMapper;
import com.rednap.finalproject.model.dto.JwtResponse;
import com.rednap.finalproject.model.dto.UserInfo;
import com.rednap.finalproject.model.dto.UserLoginRequest;
import com.rednap.finalproject.model.dto.UserRegisterRequest;
import com.rednap.finalproject.model.entity.OrderEntity;
import com.rednap.finalproject.model.entity.UserEntity;
import com.rednap.finalproject.repository.UserRepository;
import com.rednap.finalproject.security.JwtUtils;
import com.rednap.finalproject.service.UserService;
import jakarta.persistence.EntityManager;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;
    private final UserMapper userMapper;

    @Override
    public Optional<JwtResponse> authenticate(final UserLoginRequest userLoginRequest) {
        final Optional<UserEntity> optionalUserEntity = userRepository.findByUsername(userLoginRequest.getUsername());

        if(optionalUserEntity.isEmpty()) {
            return Optional.empty();
        }

        final UserEntity userEntity = optionalUserEntity.get();

        if(passwordEncoder.matches(userLoginRequest.getPassword(), userEntity.getPassHash())) {
            return generateToken(userEntity.getUsername());
        }

        return Optional.empty();
    }

    @Override
    public Optional<JwtResponse> register(final UserRegisterRequest userRegisterRequest) {
        if(userRepository.existsByUsernameOrEmail(userRegisterRequest.getUsername(), userRegisterRequest.getEmail())) {
            return Optional.empty();
        }

        final UserEntity userEntity = UserEntity.builder()
                .username(userRegisterRequest.getUsername())
                .passHash(passwordEncoder.encode(userRegisterRequest.getPassword()))
                .email(userRegisterRequest.getEmail())
                .role(UserEntity.UserRole.ROLE_USER)
                .build();

        userRepository.save(userEntity);

        return generateToken(userEntity.getUsername());
    }

    @Override
    public Optional<UserInfo> getUserInfo() {
        final Optional<UserEntity> optionalUserEntity = getCurrentUser();
        log.info("Method getUserInfo is working success!");
        return optionalUserEntity.map(userMapper::toUserInfo);
    }

    @Override
    public Optional<UserEntity> getCurrentUser() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(Objects.isNull(authentication)) {
            return Optional.empty();
        }

        final String username = authentication.getName();
        return userRepository.findByUsername(username);
    }

    @Override
    public Optional<UserEntity> getById(final Long id) {
        return userRepository.findById(id);
    }

    @Override
    public void addOrder(final OrderEntity orderEntity) {
        final Optional<UserEntity> optionalUserEntity = getCurrentUser();

        if(optionalUserEntity.isEmpty()) {
            return;
        }

        final UserEntity userEntity = optionalUserEntity.get();
        userEntity.getOrders().add(orderEntity);
        userRepository.save(userEntity);
    }

    @Override
    public void removeOrder(OrderEntity orderEntity) {
        final Optional<UserEntity> optionalUserEntity = userRepository.findByOrderId(orderEntity.getId());

        if(optionalUserEntity.isEmpty()) {
            return;
        }

        final UserEntity userEntity = optionalUserEntity.get();
        userEntity.getOrders().remove(orderEntity);
        userRepository.save(userEntity);
    }

    private Optional<JwtResponse> generateToken(final String username) {
        final String token = jwtUtils.generate(username);
        final JwtResponse jwtResponse = new JwtResponse(token);
        return Optional.of(jwtResponse);
    }
}
