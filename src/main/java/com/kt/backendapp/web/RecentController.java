package com.kt.backendapp.web;

import com.kt.backendapp.dto.RecentDtos;
import com.kt.backendapp.service.RecentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/recents")
@RequiredArgsConstructor
public class RecentController {
    private final RecentService service;

    // 최근 조회 목록 조회
    @GetMapping(produces=MediaType.APPLICATION_JSON_VALUE)
    public List<RecentDtos.RecentItem> getRecents(@RequestParam Long userId) {
        return service.getRecents(userId);
    }

    // 최근 조회 추가
    @PostMapping(produces=MediaType.APPLICATION_JSON_VALUE)
    public RecentDtos.RecentItem addRecent(@RequestBody RecentDtos.AddRecentRequest request) {
        return service.addRecent(request);
    }
}