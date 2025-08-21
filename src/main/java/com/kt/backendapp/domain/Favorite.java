package com.kt.backendapp.domain;

import lombok.*;
import java.time.OffsetDateTime;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Favorite {
    private Long id;
    private Long userId;
    private String entityType;
    private Long refId;
    private String refName;
    private String additionalInfo;
    private OffsetDateTime createdAt;

    // 비즈니스 로직 메서드들
    public boolean isBusStop() {
        return "BUS_STOP".equals(entityType);
    }

    public boolean isRoute() {
        return "ROUTE".equals(entityType);
    }

    public boolean isValid() {
        return userId != null && entityType != null && refId != null && refName != null;
    }

    public String getDisplayName() {
        return refName != null ? refName : "알 수 없음";
    }

    public boolean isRecentlyAdded() {
        if (createdAt == null) return false;
        return OffsetDateTime.now().minusDays(1).isBefore(createdAt);
    }

    public boolean isDuplicate(Long otherUserId, String otherEntityType, Long otherRefId) {
        return userId.equals(otherUserId) && 
               entityType.equals(otherEntityType) && 
               refId.equals(otherRefId);
    }
}
