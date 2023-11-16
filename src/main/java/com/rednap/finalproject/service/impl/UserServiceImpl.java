package com.rednap.finalproject.service.impl;

import com.rednap.finalproject.mapper.UserMapper;
import com.rednap.finalproject.model.dto.JwtResponse;
import com.rednap.finalproject.model.dto.UserInfo;
import com.rednap.finalproject.model.dto.UserLoginRequest;
import com.rednap.finalproject.model.dto.UserRegisterRequest;
import com.rednap.finalproject.model.entity.UserEntity;
import com.rednap.finalproject.repository.UserRepository;
import com.rednap.finalproject.security.JwtUtils;
import com.rednap.finalproject.service.UserService;
import jakarta.persistence.EntityManager;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
@AllArgsConstructor
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
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(Objects.isNull(authentication)) {
            return Optional.empty();
        }

        final String username = authentication.getName();
        final Optional<UserEntity> optionalUserEntity = userRepository.findByUsername(username);
        return optionalUserEntity.map(userMapper::toUserInfo);
    }

    private Optional<JwtResponse> generateToken(final String username) {
        final String token = jwtUtils.generate(username);
        final JwtResponse jwtResponse = new JwtResponse(token);
        return Optional.of(jwtResponse);
    }
}
