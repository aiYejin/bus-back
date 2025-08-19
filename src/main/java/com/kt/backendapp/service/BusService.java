// src/main/java/com/kt/backendapp/service/BusService.java
package com.kt.backendapp.service;

import com.kt.backendapp.client.gbis.GbisOpenApiClient;
import com.kt.backendapp.dto.bus.ArrivalDtos;
import com.kt.backendapp.dto.bus.SearchDtos;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BusService {
    private final GbisOpenApiClient api;

    /** 통합 검색: 지금은 정류장만 매핑(노선은 추후 확장) */
    public SearchDtos.SearchResponse search(String q) {
        var stops = api.searchStations(q).stream().map(s ->
            new SearchDtos.StopItem(
                s.stationId.toString(),
                s.stationName,
                s.mobileNo, // ARS
                s.y,        // lat
                s.x         // lng
            )
        ).toList();

        var routes = api.searchRoutes(q).stream().map(r ->
            new SearchDtos.RouteItem(
                r.routeId.toString(),
                r.routeName,
                r.startStationName,
                r.endStationName
            )
        ).toList();

        return new SearchDtos.SearchResponse(routes, stops);
    }

    /** 정류장 도착 */
    public ArrivalDtos.ListResponse arrivals(String stationId) {
        var items = api.getArrivals(stationId).stream().map(a ->
            new ArrivalDtos.Item(
                a.routeId.toString(),
                a.routeName,
                // 첫번째 차량
                a.predictTime1,
                a.locationNo1,
                a.flag,
                getCongestionText(a.crowded1),
                a.plateNo1,
                // 두번째 차량
                a.predictTime2,
                a.locationNo2,
                a.flag, // flag는 노선 전체 상태
                getCongestionText(a.crowded2),
                a.plateNo2
            )
        ).toList();

        return new ArrivalDtos.ListResponse(items);
    }

    private String getCongestionText(Integer crowded) {
        if (crowded == null) return null;
        return switch (crowded) {
            case 1 -> "여유";
            case 2 -> "보통";
            case 3 -> "혼잡";
            case 4 -> "매우혼잡";
            default -> null;
        };
    }
}