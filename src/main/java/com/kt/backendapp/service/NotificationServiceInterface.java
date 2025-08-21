package com.kt.backendapp.service;

import com.kt.backendapp.dto.NotificationDtos;
import java.util.List;

public interface NotificationServiceInterface {
    // 알림 목록 조회
    List<NotificationDtos.NotificationItem> getNotifications(Long userId);
    
    // 알림 추가
    NotificationDtos.NotificationItem addNotification(NotificationDtos.AddNotificationRequest request);
    
    // 알림 삭제
    void deleteNotification(Long notificationId, Long userId);
}
