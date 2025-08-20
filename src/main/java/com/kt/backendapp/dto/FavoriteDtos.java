package com.kt.backendapp.dto;

import com.kt.backendapp.entity.RefType;
import java.time.OffsetDateTime;

public final class FavoriteDtos {
    
    // 즐겨찾기 항목
    public record FavoriteItem(
        Long id,
        Long userId,
        RefType type,
        String refId,
        String refName,
        String additionalInfo,  // 추가 정보
        String alias,
        OffsetDateTime createdAt
    ) {}
    
    // 즐겨찾기 추가 요청
    public record AddFavoriteRequest(
        Long userId,
        RefType type,
        String refId,
        String refName,
        String additionalInfo,  // 추가 정보
        String alias
    ) {}
    
    // 즐겨찾기 수정 요청
    public record UpdateFavoriteRequest(
        Long userId,
        String alias
    ) {}
}