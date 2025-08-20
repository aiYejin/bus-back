package com.kt.backendapp.dto;

import com.kt.backendapp.entity.ReportType;
import java.time.OffsetDateTime;

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
        Long userId,
        String title,
        String content,
        String email,
        ReportType reportType
    ) {}
}