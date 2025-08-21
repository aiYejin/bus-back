package com.kt.backendapp.web;

import com.kt.backendapp.dto.FavoriteDtos;
import com.kt.backendapp.service.FavoriteServiceInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/api/favorites")
@RequiredArgsConstructor
public class FavoriteController {
    private final FavoriteServiceInterface service;

    // 즐겨찾기 목록 조회
    @GetMapping(produces=MediaType.APPLICATION_JSON_VALUE)
    public List<FavoriteDtos.FavoriteItem> getFavorites(@RequestParam Long userId) {
        return service.getFavorites(userId);
    }

    // 즐겨찾기 추가
    @PostMapping(produces=MediaType.APPLICATION_JSON_VALUE)
    public FavoriteDtos.FavoriteItem addFavorite(@Valid @RequestBody FavoriteDtos.AddFavoriteRequest request) {
        return service.addFavorite(request);
    }

    // 즐겨찾기 삭제
    @DeleteMapping("/{favoriteId}")
    public void deleteFavorite(@PathVariable Long favoriteId, @RequestParam Long userId) {
        service.deleteFavorite(favoriteId, userId);
    }

    // 즐겨찾기 수정 (별칭 변경)
    @PutMapping("/{favoriteId}")
    public FavoriteDtos.FavoriteItem updateFavorite(
            @PathVariable Long favoriteId,
            @Valid @RequestBody FavoriteDtos.UpdateFavoriteRequest request) {
        return service.updateFavorite(favoriteId, request);
    }
}