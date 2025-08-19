package com.kt.backendapp.repository;

import com.kt.backendapp.entity.Recent;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface RecentRepository extends JpaRepository<Recent, Long> {
    List<Recent> findTop20ByUserIdOrderByViewedAtDesc(Long userId);
}
