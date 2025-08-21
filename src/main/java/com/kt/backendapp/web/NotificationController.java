package com.kt.backendapp.web;

import com.kt.backendapp.dto.NotificationDtos;
import com.kt.backendapp.service.NotificationServiceInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/api/notifications")
@RequiredArgsConstructor
public class NotificationController {
    private final NotificationServiceInterface service;

    // 알림 목록 조회
    @GetMapping(produces=MediaType.APPLICATION_JSON_VALUE)
    public List<NotificationDtos.NotificationItem> getNotifications(@RequestParam Long userId) {
        return service.getNotifications(userId);
    }

    // 알림 설정 추가
    @PostMapping(produces=MediaType.APPLICATION_JSON_VALUE)
    public NotificationDtos.NotificationItem addNotification(@Valid @RequestBody NotificationDtos.AddNotificationRequest request) {
        return service.addNotification(request);
    }

    // 알림 삭제
    @DeleteMapping("/{notificationId}")
    public void deleteNotification(@PathVariable Long notificationId, @RequestParam Long userId) {
        service.deleteNotification(notificationId, userId);
    }
}