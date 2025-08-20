package com.kt.backendapp.service;

import com.kt.backendapp.dto.NotificationDtos;
import com.kt.backendapp.entity.Notification;
import com.kt.backendapp.entity.User;
import com.kt.backendapp.repository.NotificationRepository;
import com.kt.backendapp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationService {
    private final NotificationRepository repository;
    private final UserRepository userRepository;

    // 알림 목록 조회
    public List<NotificationDtos.NotificationItem> getNotifications(Long userId) {
        return repository.findByUserIdOrderByCreatedAtDesc(userId)
                .stream()
                .map(this::mapToNotificationItem)
                .toList();
    }

    // 알림 설정 추가
    @Transactional
    public NotificationDtos.NotificationItem addNotification(NotificationDtos.AddNotificationRequest request) {
        // 사용자 존재 확인
        User user = userRepository.findById(request.userId())
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));

        // 중복 체크
        var existingNotification = repository.findByUserIdAndStationIdAndRouteId(
                request.userId(), request.stationId(), request.routeId());

        if (existingNotification.isPresent()) {
            throw new IllegalArgumentException("이미 설정된 알림입니다.");
        }

        Notification notification = Notification.builder()
                .user(user)
                .stationId(request.stationId())
                .routeId(request.routeId())
                .alertMinutes(request.alertMinutes() != null ? request.alertMinutes() : 3)
                .startTime(request.startTime())  // 시작 시간 추가
                .build();

        Notification saved = repository.save(notification);
        return mapToNotificationItem(saved);
    }

    // 알림 삭제
    @Transactional
    public void deleteNotification(Long notificationId, Long userId) {
        Notification notification = repository.findById(notificationId)
                .orElseThrow(() -> new IllegalArgumentException("알림을 찾을 수 없습니다."));
        
        repository.delete(notification);
    }
    
    // Entity를 DTO로 변환
    private NotificationDtos.NotificationItem mapToNotificationItem(Notification notification) {
        return new NotificationDtos.NotificationItem(
                notification.getId(),
                notification.getUserIdForJson(),
                notification.getStationId(),
                notification.getRouteId(),
                notification.getAlertMinutes(),
                notification.getStartTime(),  // Add this line
                notification.getCreatedAt()
        );
    }
}