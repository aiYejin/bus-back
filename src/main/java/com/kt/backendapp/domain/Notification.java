package com.kt.backendapp.domain;

import lombok.*;
import java.time.OffsetDateTime;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Notification {
    private Long id;
    private Long userId;
    private String title;
    private String message;
    private String type;
    private Boolean isRead;
    private OffsetDateTime createdAt;

    // 비즈니스 로직 메서드들
    public void markAsRead() {
        this.isRead = true;
    }

    public boolean isUnread() {
        return !Boolean.TRUE.equals(isRead);
    }

    public boolean isImportant() {
        return "URGENT".equals(type) || "SYSTEM".equals(type);
    }

    public String getShortMessage() {
        if (message == null || message.length() <= 100) {
            return message;
        }
        return message.substring(0, 97) + "...";
    }

    public boolean isRecent() {
        if (createdAt == null) return false;
        return OffsetDateTime.now().minusDays(3).isBefore(createdAt);
    }

    public boolean isValid() {
        return title != null && !title.trim().isEmpty() &&
               message != null && !message.trim().isEmpty() &&
               type != null;
    }
}
