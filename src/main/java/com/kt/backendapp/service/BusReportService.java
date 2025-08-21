package com.kt.backendapp.service;

import com.kt.backendapp.domain.BusReport;
import com.kt.backendapp.domain.ReportType;
import com.kt.backendapp.domain.User;
import com.kt.backendapp.domain.mapper.DomainMapper;
import com.kt.backendapp.dto.BusReportDtos;
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
    private final DomainMapper domainMapper;

    // 신고 목록 조회
    public List<BusReportDtos.BusReportItem> getReports(Long userId) {
        try {
            return repository.findByUser_IdOrderByCreatedAtDesc(userId)
                    .stream()
                    .map(domainMapper::toDomain)
                    .map(this::mapToBusReportItem)
                    .toList();
        } catch (Exception e) {
            throw new RuntimeException("신고 목록 조회 중 오류가 발생했습니다: " + e.getMessage(), e);
        }
    }

    // 모든 신고 목록 조회
    public List<BusReportDtos.BusReportItem> getAllReports() {
        return repository.findAll().stream()
                .map(domainMapper::toDomain)
                .map(this::mapToBusReportItem)
                .toList();
    }

    // 신고 유형별 목록 조회
    public List<BusReportDtos.BusReportItem> getReportsByType(ReportType reportType) {
        return repository.findByReportTypeOrderByCreatedAtDesc(convertToEntityReportType(reportType))
                .stream()
                .map(domainMapper::toDomain)
                .map(this::mapToBusReportItem)
                .toList();
    }

    // 신고 등록
    @Transactional
    public BusReportDtos.BusReportItem addReport(BusReportDtos.AddBusReportRequest request) {
        // 사용자 존재 확인
        com.kt.backendapp.entity.User userEntity = userRepository.findById(request.userId())
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));

        // Domain 객체로 변환하여 비즈니스 로직 검증
        User user = domainMapper.toDomain(userEntity);
        
        BusReport report = BusReport.builder()
                .userId(request.userId())
                .title(request.title())
                .content(request.content())
                .email(request.email())
                .reportType(convertToDomainReportType(request.reportType()))
                .build();

        // Domain 비즈니스 로직 검증
        if (!report.isValidReport()) {
            throw new IllegalArgumentException("유효하지 않은 신고입니다.");
        }

        if (!report.isTitleValid()) {
            throw new IllegalArgumentException("제목이 유효하지 않습니다.");
        }

        if (!report.isContentValid()) {
            throw new IllegalArgumentException("내용이 유효하지 않습니다.");
        }

        if (!report.isEmailValid()) {
            throw new IllegalArgumentException("이메일이 유효하지 않습니다.");
        }

        // Entity로 변환하여 저장
        com.kt.backendapp.entity.BusReport reportEntity = domainMapper.toEntity(report);
        reportEntity.setUser(userEntity);
        
        com.kt.backendapp.entity.BusReport saved = repository.save(reportEntity);
        
        // Domain 객체로 변환하여 반환
        BusReport savedDomain = domainMapper.toDomain(saved);
        return mapToBusReportItem(savedDomain);
    }

    // 신고 상세 조회
    public BusReportDtos.BusReportItem getReport(Long reportId) {
        com.kt.backendapp.entity.BusReport reportEntity = repository.findById(reportId)
                .orElseThrow(() -> new IllegalArgumentException("신고를 찾을 수 없습니다."));

        BusReport report = domainMapper.toDomain(reportEntity);
        return mapToBusReportItem(report);
    }

    // Domain 객체를 DTO로 변환
    private BusReportDtos.BusReportItem mapToBusReportItem(BusReport report) {
        if (report == null) {
            throw new IllegalArgumentException("신고 정보가 null입니다.");
        }
        
        return new BusReportDtos.BusReportItem(
                report.getId(),
                report.getUserId(),
                report.getTitle(),
                report.getContent(),
                report.getEmail(),
                convertToEntityReportType(report.getReportType()),
                report.getCreatedAt()
        );
    }

    // Domain ReportType을 Entity ReportType으로 변환
    private com.kt.backendapp.entity.ReportType convertToEntityReportType(ReportType domainType) {
        return com.kt.backendapp.entity.ReportType.valueOf(domainType.name());
    }

    // Entity ReportType을 Domain ReportType으로 변환
    private ReportType convertToDomainReportType(com.kt.backendapp.entity.ReportType entityType) {
        return ReportType.valueOf(entityType.name());
    }
}