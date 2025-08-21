package com.kt.backendapp.service;

import com.kt.backendapp.dto.bus.AuthDtos;

public interface AuthServiceInterface {
    // 로그인
    AuthDtos.AuthResponse login(AuthDtos.LoginRequest request);
    
    // 회원가입
    AuthDtos.AuthResponse signup(AuthDtos.SignupRequest request);
    
    // 비밀번호 찾기
    AuthDtos.MessageResponse findPassword(AuthDtos.FindPasswordRequest request);
    
    // 디버깅용: 사용자 목록 조회
    AuthDtos.MessageResponse getAllUsers();
    
    // 사용자 정보 수정
    AuthDtos.MessageResponse updateUser(Long userId, AuthDtos.UpdateUserRequest request);
    
    // 비밀번호 변경
    AuthDtos.MessageResponse changePassword(Long userId, AuthDtos.ChangePasswordRequest request);
    
    // 계정 삭제
    AuthDtos.MessageResponse deleteAccount(Long userId, AuthDtos.DeleteAccountRequest request);
}
