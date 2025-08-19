package com.kt.backendapp.repository;

import com.kt.backendapp.entity.Favorite;
import com.kt.backendapp.entity.RefType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface FavoriteRepository extends JpaRepository<Favorite, Long> {
    List<Favorite> findByUserIdOrderByCreatedAtAsc(Long userId);
    List<Favorite> findByUserIdOrderByCreatedAtDesc(Long userId);
    List<Favorite> findByUserIdAndTypeOrderByCreatedAtAsc(Long userId, RefType type);
    List<Favorite> findByUserIdAndTypeOrderByCreatedAtDesc(Long userId, RefType type);

    Optional<Favorite> findByUserIdAndTypeAndRefId(Long userId, RefType type, String refId);

    @Transactional
    void deleteByUserIdAndTypeAndRefId(Long userId, RefType type, String refId);

    // 페이지네이션 필요하면 사용
    Page<Favorite> findByUserId(Long userId, Pageable pageable);
}
