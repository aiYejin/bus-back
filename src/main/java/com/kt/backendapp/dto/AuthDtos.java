package com.kt.backendapp.dto;

import java.time.OffsetDateTime;

public final class AuthDtos {
    // 요청 바디용
    public record LoginRequest(String email, String password) {}

    // 응답: 로그인/유저 생성 결과(비번 절대 포함 X)
    public record UserItem(Long id, String email, OffsetDateTime createdAt) {}
}
