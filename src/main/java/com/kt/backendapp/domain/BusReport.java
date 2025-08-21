package com.kt.backendapp.domain;

import lombok.*;
import java.time.OffsetDateTime;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class BusReport {
    private Long id;
    private Long userId;
    private String title;
    private String content;
    private String email;
    private ReportType reportType;
    private OffsetDateTime createdAt;

    // 비즈니스 로직 메서드들
    public boolean isValidReport() {
        return title != null && !title.trim().isEmpty() &&
               content != null && !content.trim().isEmpty() &&
               email != null && email.contains("@") &&
               reportType != null;
    }

    public boolean isTitleValid() {
        return title != null && title.length() <= 200 && !title.trim().isEmpty();
    }

    public boolean isContentValid() {
        return content != null && content.length() <= 1000 && !content.trim().isEmpty();
    }

    public boolean isEmailValid() {
        return email != null && email.contains("@") && email.contains(".");
    }

    public String getReportSummary() {
        if (content == null || content.length() <= 50) {
            return content;
        }
        return content.substring(0, 47) + "...";
    }

    public boolean isRecent() {
        if (createdAt == null) return false;
        return OffsetDateTime.now().minusDays(7).isBefore(createdAt);
    }
}
