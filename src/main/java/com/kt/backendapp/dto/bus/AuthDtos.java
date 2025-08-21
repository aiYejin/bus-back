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
    
    // 비밀번호 찾기 요청
    public record FindPasswordRequest(
        String email,
        String username
    ) {}
    
    // 로그인/회원가입 응답
    public record AuthResponse(
        Long userId,
        String username,
        String email,
        String createdAt,
        String message
    ) {}

    // 사용자 정보 수정 요청
    public record UpdateUserRequest(
        String username,
        String email
    ) {}

    // 비밀번호 변경 요청
    public record ChangePasswordRequest(
        String currentPassword,
        String newPassword
    ) {}

    // 계정 삭제 요청
    public record DeleteAccountRequest(
        String password
    ) {}

    // 일반 응답
    public record MessageResponse(
        String message
    ) {}
}
