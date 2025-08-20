package com.kt.backendapp.dto;

import java.time.OffsetDateTime;

public final class UserDtos {
    
    // 위치 업데이트 요청
    public record UpdateLocationRequest(
        Long userId,
        Double lat,
        Double lng
    ) {}
    
    // 사용자 정보 응답
    public record UserInfoResponse(
        Long id,
        String username,
        String email,
        Double currentLat,
        Double currentLng,
        OffsetDateTime locationUpdatedAt,
        OffsetDateTime createdAt
    ) {}
}