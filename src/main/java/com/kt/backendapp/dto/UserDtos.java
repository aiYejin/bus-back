package com.kt.backendapp.dto;

import java.time.OffsetDateTime;

public final class UserDtos {
    
    // 사용자 정보 응답
    public record UserInfoResponse(
        Long id,
        String username,
        String email,
        OffsetDateTime createdAt
    ) {}
}