package com.kt.backendapp.web;

import com.kt.backendapp.entity.*;
import com.kt.backendapp.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/dev")
@RequiredArgsConstructor
public class DevCrudController {
    private final UserRepository userRepo;
    private final FavoriteRepository favRepo;
    private final RecentRepository recentRepo;

    // 1) 사용자 생성/조회(모의)
    @PostMapping("/users")
    public User createUser(@RequestBody LoginReq body) {
        String email = body.email == null ? "" : body.email.trim();
        String pw    = body.password; // 평문 (프로토타입)
        return userRepo.findByEmail(email).orElseGet(() ->
            userRepo.save(User.builder().email(email).password(pw).build())
        );
    }

    // 2) 즐겨찾기 추가 (생성시각 정렬)
    @PostMapping("/favorites")
    public Favorite addFavorite(@RequestBody FavReq body) {
        var user = userRepo.findById(body.userId).orElseThrow();
        var type = RefType.valueOf(body.type.toUpperCase());
        var provider = body.provider == null ? "gg" : body.provider.toLowerCase();

        var dup = favRepo.findByUserIdAndTypeAndProviderAndRefId(user.getId(), type, provider, body.refId);
        if (dup.isPresent()) return dup.get();

        var fav = Favorite.builder()
            .user(user).type(type).refId(body.refId)
            .provider(provider).cityCode(body.cityCode).alias(body.alias)
            .build();
        return favRepo.save(fav);
    }

    // 3) 즐겨찾기 조회 (생성시각 기준: 오래된→최근)
    @GetMapping("/favorites")
    public List<Favorite> listFavorites(@RequestParam Long userId,
                                        @RequestParam(required = false, defaultValue = "asc") String order) {
        return "desc".equalsIgnoreCase(order)
            ? favRepo.findByUserIdOrderByCreatedAtDesc(userId)
            : favRepo.findByUserIdOrderByCreatedAtAsc(userId);
    }

    // 4) 최근검색 기록
    @PostMapping("/recents")
    public Recent addRecent(@RequestBody RecentReq body) {
        var user = userRepo.findById(body.userId).orElseThrow();
        var entityType = RefType.valueOf(body.entityType.toUpperCase());
        var provider = body.provider == null ? "gg" : body.provider.toLowerCase();

        var r = Recent.builder()
            .user(user).entityType(entityType).refId(body.refId)
            .provider(provider).cityCode(body.cityCode)
            .build();
        return recentRepo.save(r);
    }

    // 5) 최근검색 조회(최신 20개)
    @GetMapping("/recents")
    public List<Recent> listRecents(@RequestParam Long userId) {
        return recentRepo.findTop20ByUserIdOrderByViewedAtDesc(userId);
    }

    // DTOs
    public static class LoginReq { public String email; public String password; }
    public static class FavReq {
        public Long userId; public String type; public String refId;
        public String provider; public String cityCode; public String alias;
    }
    public static class RecentReq {
        public Long userId; public String entityType; public String refId;
        public String provider; public String cityCode;
    }
}
