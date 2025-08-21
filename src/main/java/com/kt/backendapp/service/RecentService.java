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

        cleanupOldRecents(request.userId());

        return mapToRecentItem(saved);
    }

    private void cleanupOldRecents(Long userId) {
        List<Recent> allRecents = repository.findByUserIdOrderByViewedAtDesc(userId);
        
        if (allRecents.size() > 20) {
            // 20개를 초과하는 오래된 항목들 삭제
            List<Recent> toDelete = allRecents.subList(20, allRecents.size());
            repository.deleteAll(toDelete);
        }
    }

    // 최근 조회 삭제
    @Transactional
    public void deleteRecent(Long recentId, Long userId) {
        System.out.println("RecentService.deleteRecent 호출 - recentId: " + recentId + ", userId: " + userId);
        
        Recent recent = repository.findById(recentId)
                .orElseThrow(() -> {
                    System.out.println("최근 조회 항목을 찾을 수 없음 - recentId: " + recentId);
                    return new IllegalArgumentException("최근 조회 항목을 찾을 수 없습니다.");
                });
        
        System.out.println("찾은 Recent 엔티티 - id: " + recent.getId() + ", userIdForJson: " + recent.getUserIdForJson());
        
        // 본인의 최근 조회만 삭제 가능
        if (!recent.getUserIdForJson().equals(userId)) {
            System.out.println("권한 없음 - recent.userId: " + recent.getUserIdForJson() + ", 요청 userId: " + userId);
            throw new IllegalArgumentException("본인의 최근 조회만 삭제할 수 있습니다.");
        }
        
        System.out.println("최근 조회 삭제 실행 중...");
        repository.delete(recent);
        System.out.println("최근 조회 삭제 완료");
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