// src/main/java/com/kt/backendapp/service/BusService.java
package com.kt.backendapp.service;

import com.kt.backendapp.client.gbis.GbisOpenApiClient;
import com.kt.backendapp.dto.bus.ArrivalDtos;
import com.kt.backendapp.dto.bus.SearchDtos;
import com.kt.backendapp.dto.bus.DetailDtos;
import com.kt.backendapp.entity.User;
import com.kt.backendapp.repository.UserRepository;

import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BusService {
    private final GbisOpenApiClient api;
    private final UserRepository userRepository;

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

    // 정류장 상세 정보 (도착 정보 포함)
    public DetailDtos.StationDetailResponse getStationDetail(String stationId) {
        // 1. 정류장 상세 정보
        var station = api.getStationDetail(stationId);
        if (station == null) return null;
        
        // 2. 도착 정보 가져오기
        var arrivals = api.getArrivals(stationId).stream().map(a ->
            new ArrivalDtos.Item(
                a.routeId.toString(),
                a.routeName,
                // 첫번째 차량
                a.predictTime1,
                a.locationNo1,
                getBusStatus(a.flag), // flag는 노선 전체 상태
                getCongestionText(a.crowded1),
                a.plateNo1,
                // 두번째 차량
                a.predictTime2,
                a.locationNo2,
                getBusStatus(a.flag), // flag는 노선 전체 상태
                getCongestionText(a.crowded2),
                a.plateNo2
            )
        ).toList();

        return new DetailDtos.StationDetailResponse(
            station.stationId.toString(),
            station.stationName,
            station.mobileNo,
            station.y,
            station.x,
            station.regionName,
            station.centerYn,
            arrivals
        );
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

    private String getBusStatus(String flag) {
        if (flag == null) return "N"; // 기본값은 운행종료
        return switch (flag) {
            case "RUN" -> "Y"; // 운행중
            case "PASS" -> "Y"; // 운행중
            case "STOP" -> "N"; // 운행종료
            case "WAIT" -> "Y"; // 회차지대기 (운행중으로 처리)
            default -> "N"; // 기타 상태는 운행종료로 처리
        };
    }

    // 주변 정류장 검색
    public SearchDtos.StationAroundResponse getStationsAround(String x, String y) {
        var stations = api.getStationsAround(x, y).stream().map(s ->
            new SearchDtos.StopItem(
                s.stationId.toString(),
                s.stationName,
                s.mobileNo != null ? s.mobileNo : "",  // null 체크 다시 추가
                s.y,        // lat
                s.x         // lng
            )
        ).toList();

        return new SearchDtos.StationAroundResponse(stations);
    }

    // 사용자 위치 업데이트
    @Transactional
    public void updateUserLocation(Long userId, Double lat, Double lng) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));
        
        user.setCurrentLat(lat);
        user.setCurrentLng(lng);
        user.setLocationUpdatedAt(OffsetDateTime.now());
        
        userRepository.save(user);
    }

    // 사용자 위치 기반 주변 정류장
    public SearchDtos.StationAroundResponse getNearbyStations(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));
        
        // 사용자 위치가 없으면 기본값 사용 (강남역)
        Double lat = user.getCurrentLat() != null ? user.getCurrentLat() : 37.49545;
        Double lng = user.getCurrentLng() != null ? user.getCurrentLng() : 127.0284667;
        
        return getStationsAround(lat.toString(), lng.toString());
    }
}