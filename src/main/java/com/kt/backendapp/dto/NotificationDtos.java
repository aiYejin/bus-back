package com.kt.backendapp.dto;

import java.time.OffsetDateTime;

public final class NotificationDtos {
    
    // 알림 설정 항목
    public record NotificationItem(
        Long id,
        Long userId,
        String stationId,
        String routeId,
        String stationName,
        String routeName,
        Integer alertMinutes,
        OffsetDateTime startTime,  // 시작 시간 추가
        OffsetDateTime createdAt
    ) {}
    
    // 알림 설정 요청
    public record AddNotificationRequest(
        Long userId,
        String stationId,
        String routeId,
        String stationName,
        String routeName,
        Integer alertMinutes,
        OffsetDateTime startTime  // 시작 시간 추가
    ) {}
}