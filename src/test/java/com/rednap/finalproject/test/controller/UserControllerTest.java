package com.rednap.finalproject.test.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rednap.finalproject.controller.UserController;
import com.rednap.finalproject.model.dto.UserInfo;
import com.rednap.finalproject.model.dto.UserLoginRequest;
import com.rednap.finalproject.model.dto.UserRegisterRequest;
import lombok.SneakyThrows;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = UserController.class)
@AutoConfigureMockMvc(addFilters = false)
@Import(ControllerTestConfig.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;


    @Test
    @SneakyThrows
    public void registerUserErrorTest() {
        final UserRegisterRequest userRegisterRequest = new UserRegisterRequest();
        userRegisterRequest.setEmail("test2");
        userRegisterRequest.setUsername("test2");
        userRegisterRequest.setPassword("test2");
        final String json = objectMapper.writeValueAsString(userRegisterRequest);
        mockMvc.perform(post("/api/user/register")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(json))
                .andExpect(status().is(409));
    }

    @Test
    @SneakyThrows
    public void registerUserSuccessTest() {
        final UserRegisterRequest userRegisterRequest = new UserRegisterRequest();
        userRegisterRequest.setEmail("test1");
        userRegisterRequest.setUsername("test1");
        userRegisterRequest.setPassword("test1");
        final String json = objectMapper.writeValueAsString(userRegisterRequest);
        mockMvc.perform(post("/api/user/register")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(json))
                .andExpect(status().is(200));
    }

    @Test
    @SneakyThrows
    void authenticateUserErrorTest() {
        final UserLoginRequest userLoginRequest = new UserLoginRequest();
        userLoginRequest.setUsername("test2");
        userLoginRequest.setPassword("test2");
        final String json = objectMapper.writeValueAsString(userLoginRequest);
        mockMvc.perform(post("/api/user/authenticate")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(json))
                .andExpect(status().is(401));
    }

    @Test
    @SneakyThrows
    void authenticateUserSuccessTest() {
        final UserLoginRequest userLoginRequest = new UserLoginRequest();
        userLoginRequest.setUsername("test1");
        userLoginRequest.setPassword("test1");
        final String json = objectMapper.writeValueAsString(userLoginRequest);
        mockMvc.perform(post("/api/user/authenticate")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(json))
                .andExpect(status().is(200));
    }

    @Test
    @SneakyThrows
    void getUserInfoSuccess() {
        final UserInfo userInfo = new UserInfo(1l, "test1", "testMail@gmail.com", "User");
        final String json = objectMapper.writeValueAsString(userInfo);
        mockMvc.perform(get("/api/user/info")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", Matchers.equalTo(1)));
        ;
    }

}