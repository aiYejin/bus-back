package com.kt.backendapp.dto;

import java.time.OffsetDateTime;

public final class FavoriteDtos {
    // 즐겨찾기 추가 요청 바디
    public record AddRequest(
        Long userId,
        String type,      // "ROUTE" | "STOP"
        String refId,
        String alias
    ) {}

    // 즐겨찾기 응답 아이템
    public record Item(
        Long id,
        Long userId,
        String type,
        String refId,
        String alias,
        OffsetDateTime createdAt
    ) {}
}
