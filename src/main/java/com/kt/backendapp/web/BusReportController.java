package com.kt.backendapp.web;

import com.kt.backendapp.domain.ReportType;
import com.kt.backendapp.dto.BusReportDtos;
import com.kt.backendapp.service.BusReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/api/reports")
@RequiredArgsConstructor
public class BusReportController {

    private final BusReportService service;

    // 사용자별 신고 목록 조회
    @GetMapping("/user/{userId}")
    public List<BusReportDtos.BusReportItem> getReports(@PathVariable Long userId) {
        try {
            return service.getReports(userId);
        } catch (Exception e) {
            throw new RuntimeException("사용자 신고 목록 조회 실패: " + e.getMessage(), e);
        }
    }

    // 모든 신고 목록 조회
    @GetMapping
    public List<BusReportDtos.BusReportItem> getAllReports() {
        return service.getAllReports();
    }

    // 신고 유형별 목록 조회
    @GetMapping("/type/{reportType}")
    public List<BusReportDtos.BusReportItem> getReportsByType(@PathVariable ReportType reportType) {
        return service.getReportsByType(reportType);
    }

    // 신고 상세 조회
    @GetMapping("/{reportId}")
    public BusReportDtos.BusReportItem getReport(@PathVariable Long reportId) {
        return service.getReport(reportId);
    }

    // 신고 등록
    @PostMapping
    public BusReportDtos.BusReportItem addReport(@Valid @RequestBody BusReportDtos.AddBusReportRequest request) {
        return service.addReport(request);
    }
}