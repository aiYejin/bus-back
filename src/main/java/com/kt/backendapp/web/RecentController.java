package com.kt.backendapp.web;

import com.kt.backendapp.dto.RecentDtos;
import com.kt.backendapp.service.RecentServiceInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping(value = "/api/recents", produces = "application/json")
@RequiredArgsConstructor
public class RecentController {
    private final RecentServiceInterface service;

    // 최근 조회 목록 조회
    @GetMapping(produces=MediaType.APPLICATION_JSON_VALUE)
    public List<RecentDtos.RecentItem> getRecents(@RequestParam Long userId) {
        return service.getRecents(userId);
    }

    // 최근 조회 추가
    @PostMapping(produces=MediaType.APPLICATION_JSON_VALUE)
    public RecentDtos.RecentItem addRecent(@Valid @RequestBody RecentDtos.AddRecentRequest request) {
        return service.addRecent(request);
    }

    // 최근 조회 삭제
    @DeleteMapping(value = "/{recentId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public void deleteRecent(@PathVariable("recentId") Long recentId, @RequestParam("userId") Long userId) {
        service.deleteRecent(recentId, userId);
    }
}