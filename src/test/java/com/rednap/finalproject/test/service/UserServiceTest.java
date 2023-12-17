package com.rednap.finalproject.test.service;

import com.rednap.finalproject.model.dto.JwtResponse;
import com.rednap.finalproject.model.dto.UserLoginRequest;
import com.rednap.finalproject.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith({SpringExtension.class, MockitoExtension.class})
@Import(ServiceTestConfig.class)
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    public void userAuthErrorTest() {
        final UserLoginRequest userLoginRequest = new UserLoginRequest();
        userLoginRequest.setUsername("auth_error");
        userLoginRequest.setPassword("auth_error");
        final Optional<JwtResponse> result = userService.authenticate(userLoginRequest);
        assertTrue(result.isEmpty());
    }

    @Test
    public void userAuthSuccessTest() {
        final UserLoginRequest userLoginRequest = new UserLoginRequest();
        userLoginRequest.setUsername("auth_success");
        userLoginRequest.setPassword("auth_success");
        final Optional<JwtResponse> result = userService.authenticate(userLoginRequest);
        assertTrue(result.isPresent());
        assertEquals(result.get().token(), "success");
    }

}