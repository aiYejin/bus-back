package com.kt.backendapp.web;

import com.kt.backendapp.dto.bus.AuthDtos;
import com.kt.backendapp.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService service;

    // 로그인
    @PostMapping("/login")
    public AuthDtos.AuthResponse login(@RequestBody AuthDtos.LoginRequest request) {
        return service.login(request);
    }

    // 회원가입
    @PostMapping("/signup")
    public AuthDtos.AuthResponse signup(@RequestBody AuthDtos.SignupRequest request) {
        return service.signup(request);
    }
}