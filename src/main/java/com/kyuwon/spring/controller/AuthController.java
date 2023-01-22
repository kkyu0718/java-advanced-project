package com.kyuwon.spring.controller;

import com.kyuwon.spring.dto.request.SignUpRequest;
import com.kyuwon.spring.dto.response.ApiResponse;
import com.kyuwon.spring.dto.response.ResponseCode;
import com.kyuwon.spring.dto.response.SignUpResponse;
import com.kyuwon.spring.service.SignUpService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public static class AuthController {
    private final SignUpService signUpService;

    @PostMapping("/signup")
    public ResponseEntity<ApiResponse<SignUpResponse>> signUp (@RequestBody SignUpRequest request) {
        SignUpResponse user = signUpService.saveUser(
                request.name(),
                request.email(),
                request.password(),
                request.country(),
                request.state(),
                request.city(),
                request.zipCode()
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse(ResponseCode.USER_CREATED, user));
    }


