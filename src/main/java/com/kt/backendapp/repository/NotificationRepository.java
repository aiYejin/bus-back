package com.kt.backendapp.repository;

import com.kt.backendapp.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
    // 사용자별 알림 목록
    List<Notification> findByUserIdOrderByCreatedAtDesc(Long userId);
    
    // 중복 체크용
    Optional<Notification> findByUserIdAndStationIdAndRouteId(Long userId, String stationId, String routeId);
    
    // 알림 삭제
    @Transactional
    void deleteByUserIdAndStationIdAndRouteId(Long userId, String stationId, String routeId);
}