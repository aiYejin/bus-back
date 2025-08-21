package com.kt.backendapp.dto;

import com.kt.backendapp.entity.ReportType;
import java.time.OffsetDateTime;
import jakarta.validation.constraints.*;

public final class BusReportDtos {

    // 신고 항목
    public record BusReportItem(
        Long id,
        Long userId,
        String title,
        String content,
        String email,
        ReportType reportType,
        OffsetDateTime createdAt
    ) {}

    // 신고 등록 요청
    public record AddBusReportRequest(
        @NotNull(message = "사용자 ID는 필수입니다")
        @Positive(message = "사용자 ID는 양수여야 합니다")
        Long userId,
        
        @NotBlank(message = "신고 제목은 필수입니다")
        @Size(min = 5, max = 200, message = "신고 제목은 5자 이상 200자 이하여야 합니다")
        String title,
        
        @NotBlank(message = "신고 내용은 필수입니다")
        @Size(min = 10, max = 1000, message = "신고 내용은 10자 이상 1000자 이하여야 합니다")
        String content,
        
        @NotBlank(message = "이메일은 필수입니다")
        @Email(message = "올바른 이메일 형식이 아닙니다")
        String email,
        
        @NotNull(message = "신고 유형은 필수입니다")
        ReportType reportType
    ) {}
}