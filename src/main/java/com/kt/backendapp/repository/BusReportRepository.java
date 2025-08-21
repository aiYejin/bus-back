package com.kt.backendapp.repository;

import com.kt.backendapp.entity.BusReport;
import com.kt.backendapp.entity.ReportType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BusReportRepository extends JpaRepository<BusReport, Long> {

    // 사용자별 신고 목록
    List<BusReport> findByUser_IdOrderByCreatedAtDesc(Long userId);

    // 신고 유형별 목록
    List<BusReport> findByReportTypeOrderByCreatedAtDesc(ReportType reportType);
}