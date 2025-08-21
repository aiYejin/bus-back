package com.kt.backendapp.dto;

import com.kt.backendapp.entity.RefType;
import java.time.OffsetDateTime;
import jakarta.validation.constraints.*;

public final class RecentDtos {
    
    // 최근 조회 항목
    public record RecentItem(
        Long id,
        Long userId,
        RefType entityType,
        String refId,
        String refName,
        String additionalInfo,  // 추가 정보
        OffsetDateTime viewedAt
    ) {}
    
    // 최근 조회 추가 요청
    public record AddRecentRequest(
        @NotNull(message = "사용자 ID는 필수입니다")
        @Positive(message = "사용자 ID는 양수여야 합니다")
        Long userId,
        
        @NotNull(message = "엔티티 유형은 필수입니다")
        RefType entityType,
        
        @NotBlank(message = "참조 ID는 필수입니다")
        @Size(max = 64, message = "참조 ID는 64자 이하여야 합니다")
        String refId,
        
        @NotBlank(message = "참조 이름은 필수입니다")
        @Size(max = 100, message = "참조 이름은 100자 이하여야 합니다")
        String refName,
        
        @Size(max = 200, message = "추가 정보는 200자 이하여야 합니다")
        String additionalInfo
    ) {}
}