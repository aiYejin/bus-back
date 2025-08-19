package com.kt.backendapp.dto;

import java.time.OffsetDateTime;

public final class RecentDtos {
    // 최근검색 추가 요청 바디
    public record AddRequest(
        Long userId,
        String entityType, // "ROUTE" | "STOP"
        String refId
    ) {}

    // 최근검색 응답 아이템
    public record Item(
        Long id,
        Long userId,
        String entityType,
        String refId,
        OffsetDateTime viewedAt
    ) {}
}
