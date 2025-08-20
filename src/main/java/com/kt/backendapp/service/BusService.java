// src/main/java/com/kt/backendapp/service/BusService.java
package com.kt.backendapp.service;

import com.kt.backendapp.client.gbis.GbisOpenApiClient;
import com.kt.backendapp.dto.bus.ArrivalDtos;
import com.kt.backendapp.dto.bus.SearchDtos;
import com.kt.backendapp.dto.bus.DetailDtos;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BusService {
    private final GbisOpenApiClient api;

    // 검색 조회
    public SearchDtos.SearchResponse search(String q) {
        var stops = api.getStations(q).stream().map(s ->
            new SearchDtos.StopItem(
                s.stationId.toString(),
                s.stationName,
                s.mobileNo, // ARS
                s.y,        // lat
                s.x         // lng
            )
        ).toList();

        var routes = api.getRoutes(q).stream().map(r ->
            new SearchDtos.RouteItem(
                r.routeId.toString(),
                r.routeName,
                r.startStationName,
                r.endStationName
            )
        ).toList();

        return new SearchDtos.SearchResponse(routes, stops);
    }

    // 정류장 도착
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


    // 노선 상세 정보 (정류장 목록 포함)
    public DetailDtos.RouteDetailResponse getRouteDetail(String routeId) {
        var route = api.getRoute(routeId);
        if (route == null) return null;
        
        // 노선별 정류장 목록 가져오기
        var stations = api.getRouteStations(routeId).stream().map(s ->
            new DetailDtos.StationItem(
                s.stationId.toString(),
                s.stationName,
                s.mobileNo,
                s.stationSeq,
                s.y,        // lat
                s.x,        // lng
                s.regionName,
                s.adminName,
                s.centerYn,
                s.turnYn,
                s.turnSeq
            )
        ).toList();
        
        return new DetailDtos.RouteDetailResponse(
            route.routeId.toString(),
            route.routeName,
            route.startStationName,
            route.endStationName,
            route.routeTypeName,
            route.regionName,
            route.adminName,
            route.companyName,
            route.companyTel,
            route.garageName,
            route.garageTel,
            route.upFirstTime.toString(),     
            route.upLastTime.toString(),        
            route.downFirstTime.toString(),  
            route.downLastTime.toString(), 
            route.peekAlloc,
            route.nPeekAlloc,
            route.turnStNm,
            stations
        );
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