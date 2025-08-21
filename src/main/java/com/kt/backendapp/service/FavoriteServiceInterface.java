package com.kt.backendapp.service;

import com.kt.backendapp.dto.FavoriteDtos;
import java.util.List;

public interface FavoriteServiceInterface {
    // 즐겨찾기 목록 조회
    List<FavoriteDtos.FavoriteItem> getFavorites(Long userId);
    
    // 즐겨찾기 추가
    FavoriteDtos.FavoriteItem addFavorite(FavoriteDtos.AddFavoriteRequest request);
    
    // 즐겨찾기 삭제
    void deleteFavorite(Long favoriteId, Long userId);
    
    // 즐겨찾기 수정
    FavoriteDtos.FavoriteItem updateFavorite(Long favoriteId, FavoriteDtos.UpdateFavoriteRequest request);
}
