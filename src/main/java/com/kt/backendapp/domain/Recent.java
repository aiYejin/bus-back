package com.kt.backendapp.domain;

import lombok.*;
import java.time.OffsetDateTime;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Recent {
    private Long id;
    private Long userId;
    private String entityType;
    private Long refId;
    private String refName;
    private String additionalInfo;
    private OffsetDateTime viewedAt;

    // 비즈니스 로직 메서드들
    public boolean isRecent() {
        if (viewedAt == null) return false;
        return OffsetDateTime.now().minusDays(7).isBefore(viewedAt);
    }

    public boolean isBusStop() {
        return "BUS_STOP".equals(entityType);
    }

    public boolean isRoute() {
        return "ROUTE".equals(entityType);
    }

    public void updateViewTime() {
        this.viewedAt = OffsetDateTime.now();
    }

    public String getDisplayName() {
        return refName != null ? refName : "알 수 없음";
    }

    public boolean isValid() {
        return userId != null && entityType != null && refId != null && refName != null;
    }

    public boolean isExpired() {
        if (viewedAt == null) return true;
        return OffsetDateTime.now().minusDays(30).isAfter(viewedAt);
    }
}
