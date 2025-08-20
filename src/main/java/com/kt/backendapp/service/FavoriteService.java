package com.kt.backendapp.service;

import com.kt.backendapp.dto.FavoriteDtos;
import com.kt.backendapp.entity.Favorite;
import com.kt.backendapp.entity.User;
import com.kt.backendapp.repository.FavoriteRepository;
import com.kt.backendapp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FavoriteService {
    private final FavoriteRepository repository;
    private final UserRepository userRepository;

    // 즐겨찾기 목록 조회
    public List<FavoriteDtos.FavoriteItem> getFavorites(Long userId) {
        return repository.findByUserIdOrderByCreatedAtDesc(userId)
                .stream()
                .map(this::mapToFavoriteItem)
                .toList();
    }

    // 즐겨찾기 추가
    @Transactional
    public FavoriteDtos.FavoriteItem addFavorite(FavoriteDtos.AddFavoriteRequest request) {
        // 사용자 존재 확인
        User user = userRepository.findById(request.userId())
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));
    
        // 중복 체크
        if (repository.findByUserIdAndTypeAndRefId(request.userId(), request.type(), request.refId()).isPresent()) {
            throw new IllegalArgumentException("이미 즐겨찾기에 추가된 항목입니다.");
        }
    
        Favorite favorite = Favorite.builder()
                .user(user)
                .type(request.type())
                .refId(request.refId())
                .refName(request.refName())  // 노선명/정류장명 추가
                .alias(request.alias())
                .build();
    
        Favorite saved = repository.save(favorite);
        return mapToFavoriteItem(saved);
    }

    // 즐겨찾기 삭제
    @Transactional
    public void deleteFavorite(Long favoriteId, Long userId) {
        Favorite favorite = repository.findById(favoriteId)
                .orElseThrow(() -> new IllegalArgumentException("즐겨찾기를 찾을 수 없습니다."));
        
        // 본인의 즐겨찾기만 삭제 가능
        if (!favorite.getUserIdForJson().equals(userId)) {
            throw new IllegalArgumentException("본인의 즐겨찾기만 삭제할 수 있습니다.");
        }
        
        repository.delete(favorite);
    }

    // 즐겨찾기 수정 (별칭 변경)
    @Transactional
    public FavoriteDtos.FavoriteItem updateFavorite(Long favoriteId, FavoriteDtos.UpdateFavoriteRequest request) {
        Favorite favorite = repository.findById(favoriteId)
                .orElseThrow(() -> new IllegalArgumentException("즐겨찾기를 찾을 수 없습니다."));
        
        // 본인의 즐겨찾기만 수정 가능
        if (!favorite.getUserIdForJson().equals(request.userId())) {
            throw new IllegalArgumentException("본인의 즐겨찾기만 수정할 수 있습니다.");
        }
        
        favorite.setAlias(request.alias());
        
        Favorite saved = repository.save(favorite);
        return mapToFavoriteItem(saved);
    }

    // Entity를 DTO로 변환
    private FavoriteDtos.FavoriteItem mapToFavoriteItem(Favorite favorite) {
        return new FavoriteDtos.FavoriteItem(
                favorite.getId(),
                favorite.getUserIdForJson(),
                favorite.getType(),
                favorite.getRefId(),
                favorite.getRefName(),  // 노선명/정류장명 추가
                favorite.getAlias(),
                favorite.getCreatedAt()
        );
    }
}