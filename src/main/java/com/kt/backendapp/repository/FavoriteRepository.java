package com.kt.backendapp.repository;

import com.kt.backendapp.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface FavoriteRepository extends JpaRepository<Favorite, Long> {
    // 생성 시각 기준 정렬
    List<Favorite> findByUserIdOrderByCreatedAtAsc(Long userId);
    List<Favorite> findByUserIdOrderByCreatedAtDesc(Long userId);

    // 타입별 정렬 조회 (필요 시)
    List<Favorite> findByUserIdAndTypeOrderByCreatedAtAsc(Long userId, RefType type);

    Optional<Favorite> findByUserIdAndTypeAndProviderAndRefId(Long userId, RefType type, String provider, String refId);
}
