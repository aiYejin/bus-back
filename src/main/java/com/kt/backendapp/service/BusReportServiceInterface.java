package com.kt.backendapp.service;

import com.kt.backendapp.domain.ReportType;
import com.kt.backendapp.dto.BusReportDtos;
import java.util.List;

public interface BusReportServiceInterface {
    // 신고 목록 조회
    List<BusReportDtos.BusReportItem> getReports(Long userId);
    
    // 모든 신고 목록 조회
    List<BusReportDtos.BusReportItem> getAllReports();
    
    // 신고 유형별 목록 조회
    List<BusReportDtos.BusReportItem> getReportsByType(ReportType reportType);
    
    // 신고 상세 조회
    BusReportDtos.BusReportItem getReport(Long reportId);
    
    // 신고 등록
    BusReportDtos.BusReportItem addReport(BusReportDtos.AddBusReportRequest request);
}
