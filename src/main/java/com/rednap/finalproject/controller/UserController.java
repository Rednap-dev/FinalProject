package com.rednap.finalproject.controller;

import com.rednap.finalproject.model.dto.JwtResponse;
import com.rednap.finalproject.model.dto.UserInfo;
import com.rednap.finalproject.model.dto.UserLoginRequest;
import com.rednap.finalproject.model.dto.UserRegisterRequest;
import com.rednap.finalproject.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins = "*", methods = {RequestMethod.POST, RequestMethod.GET})
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/authenticate")
    public ResponseEntity authenticate(@RequestBody UserLoginRequest userLoginRequest) {
        Optional<JwtResponse> jwtResponse = userService.authenticate(userLoginRequest);

        if(jwtResponse.isPresent()) {
            return ResponseEntity.ok(jwtResponse.get());
        }

        return ResponseEntity.status(401).build();
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody UserRegisterRequest userRegisterRequest) {
        Optional<JwtResponse> jwtResponse = userService.register(userRegisterRequest);

        if(jwtResponse.isPresent()) {
            return ResponseEntity.ok(jwtResponse.get());
        }

        return ResponseEntity.status(409).build();
    }

    @GetMapping("/info")
    public ResponseEntity getUserInfo() {
        final Optional<UserInfo> userInfoOptional = userService.getUserInfo();

        if(userInfoOptional.isEmpty()) {
            return ResponseEntity.status(403).build();
        }

        return ResponseEntity.ok(userInfoOptional.get());
    }

}
