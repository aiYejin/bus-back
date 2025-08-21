package com.kt.backendapp.web;

import com.kt.backendapp.dto.bus.AuthDtos;
import com.kt.backendapp.service.AuthServiceInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthServiceInterface service;

    // 로그인
    @PostMapping("/login")
    public AuthDtos.AuthResponse login(@Valid @RequestBody AuthDtos.LoginRequest request) {
        return service.login(request);
    }

    // 회원가입
    @PostMapping("/signup")
    public AuthDtos.AuthResponse signup(@Valid @RequestBody AuthDtos.SignupRequest request) {
        return service.signup(request);
    }

    // 비밀번호 찾기
    @PostMapping("/find-password")
    public AuthDtos.MessageResponse findPassword(@Valid @RequestBody AuthDtos.FindPasswordRequest request) {
        return service.findPassword(request);
    }

    // 디버깅용: 사용자 목록 조회
    @GetMapping("/users")
    public AuthDtos.MessageResponse getAllUsers() {
        return service.getAllUsers();
    }

    // 사용자 정보 수정
    @PutMapping("/users/{userId}")
    public AuthDtos.MessageResponse updateUser(
            @PathVariable Long userId,
            @Valid @RequestBody AuthDtos.UpdateUserRequest request) {
        return service.updateUser(userId, request);
    }

    // 비밀번호 변경
    @PutMapping("/users/{userId}/password")
    public AuthDtos.MessageResponse changePassword(
            @PathVariable Long userId,
            @Valid @RequestBody AuthDtos.ChangePasswordRequest request) {
        return service.changePassword(userId, request);
    }

    // 계정 삭제
    @DeleteMapping("/users/{userId}")
    public AuthDtos.MessageResponse deleteAccount(
            @PathVariable Long userId,
            @Valid @RequestBody AuthDtos.DeleteAccountRequest request) {
        return service.deleteAccount(userId, request);
    }
}