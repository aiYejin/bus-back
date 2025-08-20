package com.kt.backendapp.repository;

import com.kt.backendapp.entity.Recent;
import com.kt.backendapp.entity.RefType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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

    @Modifying
    @Transactional
    @Query("DELETE FROM Recent r WHERE r.user.id = :userId AND r.id NOT IN " +
        "(SELECT r2.id FROM Recent r2 WHERE r2.user.id = :userId ORDER BY r2.viewedAt DESC LIMIT 20)")
    void deleteOldRecents(@Param("userId") Long userId);

    List<Recent> findByUserIdOrderByViewedAtDesc(Long userId);
}