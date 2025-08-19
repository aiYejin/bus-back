package com.kt.backendapp.repository;

import com.kt.backendapp.entity.Recent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RecentRepository extends JpaRepository<Recent, Long> {
    // 최신 20개
    List<Recent> findTop20ByUserIdOrderByViewedAtDesc(Long userId);

    // 페이지네이션이 필요할 때
    Page<Recent> findByUserIdOrderByViewedAtDesc(Long userId, Pageable pageable);

    // “최근 20개 유지” 같은 정리용 보조 쿼리 (서비스에서 사용)
    @Query("select r.id from Recent r where r.user.id = :userId order by r.viewedAt desc")
    List<Long> findAllIdsByUserOrderByViewedAtDesc(@Param("userId") Long userId);
}
