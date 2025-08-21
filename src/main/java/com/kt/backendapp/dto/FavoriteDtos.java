package com.kt.backendapp.dto;

import com.kt.backendapp.entity.RefType;
import java.time.OffsetDateTime;
import jakarta.validation.constraints.*;

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
        @NotNull(message = "사용자 ID는 필수입니다")
        @Positive(message = "사용자 ID는 양수여야 합니다")
        Long userId,
        
        @NotNull(message = "즐겨찾기 유형은 필수입니다")
        RefType type,
        
        @NotBlank(message = "참조 ID는 필수입니다")
        @Size(max = 64, message = "참조 ID는 64자 이하여야 합니다")
        String refId,
        
        @NotBlank(message = "참조 이름은 필수입니다")
        @Size(max = 100, message = "참조 이름은 100자 이하여야 합니다")
        String refName,
        
        @Size(max = 200, message = "추가 정보는 200자 이하여야 합니다")
        String additionalInfo,  // 추가 정보
        
        @Size(max = 100, message = "별칭은 100자 이하여야 합니다")
        String alias
    ) {}
    
    // 즐겨찾기 수정 요청
    public record UpdateFavoriteRequest(
        @NotNull(message = "사용자 ID는 필수입니다")
        @Positive(message = "사용자 ID는 양수여야 합니다")
        Long userId,
        
        @Size(max = 100, message = "별칭은 100자 이하여야 합니다")
        String alias
    ) {}
}