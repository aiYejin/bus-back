package com.kt.backendapp.domain.mapper;

import com.kt.backendapp.domain.User;
import com.kt.backendapp.domain.BusReport;
import com.kt.backendapp.domain.Notification;
import com.kt.backendapp.domain.Recent;
import com.kt.backendapp.domain.Favorite;
import com.kt.backendapp.domain.ReportType;
import com.kt.backendapp.entity.*;
import org.springframework.stereotype.Component;

@Component
public class DomainMapper {

    // User 변환
    public User toDomain(com.kt.backendapp.entity.User entity) {
        if (entity == null) return null;
        
        return User.builder()
                .id(entity.getId())
                .username(entity.getUsername())
                .email(entity.getEmail())
                .password(entity.getPassword())
                .currentLat(entity.getCurrentLat())
                .currentLng(entity.getCurrentLng())
                .locationUpdatedAt(entity.getLocationUpdatedAt())
                .createdAt(entity.getCreatedAt())
                .build();
    }

    public com.kt.backendapp.entity.User toEntity(User domain) {
        if (domain == null) return null;
        
        return com.kt.backendapp.entity.User.builder()
                .id(domain.getId())
                .username(domain.getUsername())
                .email(domain.getEmail())
                .password(domain.getPassword())
                .currentLat(domain.getCurrentLat())
                .currentLng(domain.getCurrentLng())
                .locationUpdatedAt(domain.getLocationUpdatedAt())
                .createdAt(domain.getCreatedAt())
                .build();
    }

    // BusReport 변환
    public BusReport toDomain(com.kt.backendapp.entity.BusReport entity) {
        if (entity == null) return null;
        
        Long userId = null;
        if (entity.getUser() != null) {
            userId = entity.getUser().getId();
        }
        
        return BusReport.builder()
                .id(entity.getId())
                .userId(userId)
                .title(entity.getTitle())
                .content(entity.getContent())
                .email(entity.getEmail())
                .reportType(ReportType.valueOf(entity.getReportType().name()))
                .createdAt(entity.getCreatedAt())
                .build();
    }

    public com.kt.backendapp.entity.BusReport toEntity(BusReport domain) {
        if (domain == null) return null;
        
        return com.kt.backendapp.entity.BusReport.builder()
                .id(domain.getId())
                .title(domain.getTitle())
                .content(domain.getContent())
                .email(domain.getEmail())
                .reportType(com.kt.backendapp.entity.ReportType.valueOf(domain.getReportType().name()))
                .createdAt(domain.getCreatedAt())
                .build();
    }

    // Notification 변환
    public Notification toDomain(com.kt.backendapp.entity.Notification entity) {
        if (entity == null) return null;
        
        Long userId = entity.getUserIdForJson();
        if (userId == null && entity.getUser() != null) {
            userId = entity.getUser().getId();
        }
        
        return Notification.builder()
                .id(entity.getId())
                .userId(userId)
                .title("버스 알림") // 기본 제목
                .message("정류장 " + entity.getStationId() + " 알림")
                .type("BUS_ALERT")
                .isRead(false)
                .createdAt(entity.getCreatedAt())
                .build();
    }

    public com.kt.backendapp.entity.Notification toEntity(Notification domain) {
        if (domain == null) return null;
        
        return com.kt.backendapp.entity.Notification.builder()
                .id(domain.getId())
                .stationId("default") // 기본값
                .routeId(null)
                .alertMinutes(5) // 기본값
                .createdAt(domain.getCreatedAt())
                .build();
    }

    // Recent 변환
    public Recent toDomain(com.kt.backendapp.entity.Recent entity) {
        if (entity == null) return null;
        
        Long userId = entity.getUserIdForJson();
        if (userId == null && entity.getUser() != null) {
            userId = entity.getUser().getId();
        }
        
        return Recent.builder()
                .id(entity.getId())
                .userId(userId)
                .entityType(entity.getEntityType().name())
                .refId(Long.parseLong(entity.getRefId()))
                .refName(entity.getRefName())
                .additionalInfo(entity.getAdditionalInfo())
                .viewedAt(entity.getViewedAt())
                .build();
    }

    public com.kt.backendapp.entity.Recent toEntity(Recent domain) {
        if (domain == null) return null;
        
        return com.kt.backendapp.entity.Recent.builder()
                .id(domain.getId())
                .entityType(com.kt.backendapp.entity.RefType.valueOf(domain.getEntityType()))
                .refId(domain.getRefId().toString())
                .refName(domain.getRefName())
                .additionalInfo(domain.getAdditionalInfo())
                .viewedAt(domain.getViewedAt())
                .build();
    }

    // Favorite 변환
    public Favorite toDomain(com.kt.backendapp.entity.Favorite entity) {
        if (entity == null) return null;
        
        Long userId = entity.getUserIdForJson();
        if (userId == null && entity.getUser() != null) {
            userId = entity.getUser().getId();
        }
        
        return Favorite.builder()
                .id(entity.getId())
                .userId(userId)
                .entityType(entity.getType().name())
                .refId(Long.parseLong(entity.getRefId()))
                .refName(entity.getRefName())
                .additionalInfo(entity.getAdditionalInfo())
                .createdAt(entity.getCreatedAt())
                .build();
    }

    public com.kt.backendapp.entity.Favorite toEntity(Favorite domain) {
        if (domain == null) return null;
        
        return com.kt.backendapp.entity.Favorite.builder()
                .id(domain.getId())
                .type(com.kt.backendapp.entity.RefType.valueOf(domain.getEntityType()))
                .refId(domain.getRefId().toString())
                .refName(domain.getRefName())
                .additionalInfo(domain.getAdditionalInfo())
                .createdAt(domain.getCreatedAt())
                .build();
    }
}
