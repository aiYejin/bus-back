package com.kt.backendapp.dto.bus;

public final class AuthDtos {
    
    // 로그인 요청
    public record LoginRequest(
        String email,
        String password
    ) {}
    
    // 회원가입 요청
    public record SignupRequest(
        String username,
        String email,
        String password
    ) {}
    
    // 로그인/회원가입 응답
    public record AuthResponse(
        Long userId,
        String username,
        String email,
        String message
    ) {}
}
