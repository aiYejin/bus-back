package com.kt.backendapp.service;

import com.kt.backendapp.dto.RecentDtos;
import com.kt.backendapp.entity.Recent;
import com.kt.backendapp.entity.User;
import com.kt.backendapp.repository.RecentRepository;
import com.kt.backendapp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RecentService {
    private final RecentRepository repository;
    private final UserRepository userRepository;

    // 최근 조회 목록 조회
    public List<RecentDtos.RecentItem> getRecents(Long userId) {
        return repository.findTop20ByUserIdOrderByViewedAtDesc(userId)
                .stream()
                .map(this::mapToRecentItem)
                .toList();
    }

    // 최근 조회 추가
    @Transactional
    public RecentDtos.RecentItem addRecent(RecentDtos.AddRecentRequest request) {
        // 사용자 존재 확인
        User user = userRepository.findById(request.userId())
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));

        // 기존 항목이 있는지 확인
        var existingRecent = repository.findByUserIdAndEntityTypeAndRefId(
                request.userId(), request.entityType(), request.refId());

        if (existingRecent.isPresent()) {
            // 기존 항목이 있으면 삭제 (최신 항목만 남기기)
            repository.delete(existingRecent.get());
        }

        // 새 항목 추가
        Recent recent = Recent.builder()
                .user(user)
                .entityType(request.entityType())
                .refId(request.refId())
                .refName(request.refName())
                .additionalInfo(request.additionalInfo())
                .build();

        Recent saved = repository.save(recent);
        return mapToRecentItem(saved);
    }

    // Entity를 DTO로 변환
    private RecentDtos.RecentItem mapToRecentItem(Recent recent) {
        return new RecentDtos.RecentItem(
                recent.getId(),
                recent.getUserIdForJson(),
                recent.getEntityType(),
                recent.getRefId(),
                recent.getRefName(),
                recent.getAdditionalInfo(),
                recent.getViewedAt()
        );
    }
}