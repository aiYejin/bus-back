package com.kt.backendapp.dto;

import com.kt.backendapp.entity.RefType;
import java.time.OffsetDateTime;

public final class RecentDtos {
    // 최근검색 추가 요청 바디
    public record AddRequest(
        Long userId,
        RefType entityType, // ROUTE | STOP
        String refId
    ) {}

    // 최근검색 응답 아이템
    public record Item(
        Long id,
        Long userId,
        RefType entityType, // ROUTE | STOP
        String refId,
        OffsetDateTime viewedAt
    ) {}
}