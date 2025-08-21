package com.kt.backendapp.dto;

import java.time.OffsetDateTime;
import jakarta.validation.constraints.*;

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
        @NotNull(message = "사용자 ID는 필수입니다")
        @Positive(message = "사용자 ID는 양수여야 합니다")
        Long userId,
        
        @NotBlank(message = "정류장 ID는 필수입니다")
        @Size(max = 64, message = "정류장 ID는 64자 이하여야 합니다")
        String stationId,
        
        @Size(max = 64, message = "노선 ID는 64자 이하여야 합니다")
        String routeId,
        
        @Size(max = 100, message = "정류장 이름은 100자 이하여야 합니다")
        String stationName,
        
        @Size(max = 100, message = "노선 이름은 100자 이하여야 합니다")
        String routeName,
        
        @NotNull(message = "알림 시간(분)은 필수입니다")
        @Min(value = 1, message = "알림 시간은 최소 1분 이상이어야 합니다")
        @Max(value = 60, message = "알림 시간은 최대 60분 이하여야 합니다")
        Integer alertMinutes,
        
        OffsetDateTime startTime  // 시작 시간 추가
    ) {}
}