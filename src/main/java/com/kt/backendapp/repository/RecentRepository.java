package com.kt.backendapp.repository;

import com.kt.backendapp.entity.Recent;
import com.kt.backendapp.entity.RefType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface RecentRepository extends JpaRepository<Recent, Long> {
    // 사용자별 최근 조회 목록 (최신순, 최대 20개)
    List<Recent> findTop20ByUserIdOrderByViewedAtDesc(Long userId);
    
    // 특정 항목 조회 (중복 체크용)
    Optional<Recent> findByUserIdAndEntityTypeAndRefId(Long userId, RefType entityType, String refId);
    
    // 중복 항목 삭제 (최신 항목만 남기기)
    @Transactional
    void deleteByUserIdAndEntityTypeAndRefIdAndIdNot(Long userId, RefType entityType, String refId, Long keepId);
}