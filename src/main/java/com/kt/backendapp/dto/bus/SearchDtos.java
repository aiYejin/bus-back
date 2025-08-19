// src/main/java/com/kt/backendapp/dto/bus/SearchDtos.java
package com.kt.backendapp.dto.bus;

import java.util.List;

/** 통합검색 응답 DTO 묶음 */
public final class SearchDtos {

    /** 노선 결과 1건 (지금은 미사용이면 남겨만 두세요) */
    public record RouteItem(
        String routeId,
        String routeName,
        String startStationName,
        String endStationName
    ) {}

    /** 정류장 결과 1건 */
    public record StopItem(
        String stationId,
        String stationName,
        String arsId,
        Double lat,
        Double lng
    ) {}

    /** 검색 응답: 노선/정류장 배열 */
    public record SearchResponse(
        List<RouteItem> routes,
        List<StopItem> stops
    ) {}
}
