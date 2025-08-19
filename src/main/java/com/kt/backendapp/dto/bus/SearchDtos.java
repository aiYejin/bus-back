// src/main/java/com/kt/backendapp/dto/bus/SearchDtos.java
package com.kt.backendapp.dto.bus;

import java.util.List;

// 통합검색 응답 DTO 묶음
public final class SearchDtos {

    // 노선 결과 1건 (지금은 미사용이면 남겨만 두세요)
    public record RouteItem(
        String routeId,        // 노선 ID
        String routeName,      // 노선명
        String startStationName, // 기점 정류장명
        String endStationName    // 종점 정류장명
    ) {}

    // 정류장 결과 1건
    public record StopItem(
        String stationId,  // 정류장 ID
        String stationName, // 정류장명
        String arsId,      // ARS 번호
        Double lat,        // 위도
        Double lng         // 경도
    ) {}

    // 검색 응답: 노선/정류장 배열
    public record SearchResponse(
        List<RouteItem> routes, // 노선 목록 (현재는 빈 배열)
        List<StopItem> stops   // 정류장 목록
    ) {}
}