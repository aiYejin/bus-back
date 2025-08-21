package com.kt.backendapp.service;

import com.kt.backendapp.dto.RecentDtos;
import java.util.List;

public interface RecentServiceInterface {
    // 최근 조회 목록 조회
    List<RecentDtos.RecentItem> getRecents(Long userId);
    
    // 최근 조회 추가
    RecentDtos.RecentItem addRecent(RecentDtos.AddRecentRequest request);
    
    // 최근 조회 삭제
    void deleteRecent(Long recentId, Long userId);
}
