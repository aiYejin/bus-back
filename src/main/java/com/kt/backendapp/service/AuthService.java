package com.kt.backendapp.service;

import com.kt.backendapp.dto.bus.AuthDtos;
import com.kt.backendapp.entity.User;
import com.kt.backendapp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository repository;

    // 로그인
    public AuthDtos.AuthResponse login(AuthDtos.LoginRequest request) {
        User user = repository.findByEmailAndPassword(request.email(), request.password())
                .orElseThrow(() -> new IllegalArgumentException("이메일 또는 비밀번호가 올바르지 않습니다."));
        
        return new AuthDtos.AuthResponse(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                "로그인 성공"
        );
    }

    // 회원가입
    @Transactional
    public AuthDtos.AuthResponse signup(AuthDtos.SignupRequest request) {
        // 이메일 중복 체크
        if (repository.existsByEmail(request.email())) {
            throw new IllegalArgumentException("이미 사용 중인 이메일입니다.");
        }

        User user = User.builder()
                .username(request.username())
                .email(request.email())
                .password(request.password())
                .build();

        User saved = repository.save(user);
        
        return new AuthDtos.AuthResponse(
                saved.getId(),
                saved.getUsername(),
                saved.getEmail(),
                "회원가입 성공"
        );
    }
}