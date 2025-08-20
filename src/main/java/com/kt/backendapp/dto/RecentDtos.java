package com.kt.backendapp.dto;

import com.kt.backendapp.entity.RefType;
import java.time.OffsetDateTime;

public final class RecentDtos {
    
    // 최근 조회 항목
    public record RecentItem(
        Long id,
        Long userId,
        RefType entityType,
        String refId,
        String refName,      // 노선명 또는 정류장명
        OffsetDateTime viewedAt
    ) {}
    
    // 최근 조회 추가 요청
    public record AddRecentRequest(
        Long userId,
        RefType entityType,
        String refId,
        String refName
    ) {}
}