package com.kt.backendapp.service;

import com.kt.backendapp.dto.BusReportDtos;
import com.kt.backendapp.entity.BusReport;
import com.kt.backendapp.entity.ReportType;
import com.kt.backendapp.entity.User;
import com.kt.backendapp.repository.BusReportRepository;
import com.kt.backendapp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BusReportService {

    private final BusReportRepository repository;
    private final UserRepository userRepository;

    // 신고 목록 조회
    public List<BusReportDtos.BusReportItem> getReports(Long userId) {
        return repository.findByUserIdOrderByCreatedAtDesc(userId)
                .stream()
                .map(this::mapToBusReportItem)
                .toList();
    }

    // 모든 신고 목록 조회
    public List<BusReportDtos.BusReportItem> getAllReports() {
        return repository.findAll().stream()
                .map(this::mapToBusReportItem)
                .toList();
    }

    // 신고 유형별 목록 조회
    public List<BusReportDtos.BusReportItem> getReportsByType(ReportType reportType) {
        return repository.findByReportTypeOrderByCreatedAtDesc(reportType)
                .stream()
                .map(this::mapToBusReportItem)
                .toList();
    }

    // 신고 등록
    @Transactional
    public BusReportDtos.BusReportItem addReport(BusReportDtos.AddBusReportRequest request) {
        // 사용자 존재 확인
        User user = userRepository.findById(request.userId())
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));

        BusReport report = BusReport.builder()
                .user(user)
                .title(request.title())
                .content(request.content())
                .email(request.email())
                .reportType(request.reportType())
                .build();

        BusReport saved = repository.save(report);
        return mapToBusReportItem(saved);
    }

    // 신고 상세 조회
    public BusReportDtos.BusReportItem getReport(Long reportId) {
        BusReport report = repository.findById(reportId)
                .orElseThrow(() -> new IllegalArgumentException("신고를 찾을 수 없습니다."));

        return mapToBusReportItem(report);
    }

    // Entity를 DTO로 변환
    private BusReportDtos.BusReportItem mapToBusReportItem(BusReport report) {
        return new BusReportDtos.BusReportItem(
                report.getId(),
                report.getUserIdForJson(),
                report.getTitle(),
                report.getContent(),
                report.getEmail(),
                report.getReportType(),
                report.getCreatedAt()
        );
    }
}