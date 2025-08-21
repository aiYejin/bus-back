package com.kt.backendapp.service;

import com.kt.backendapp.dto.bus.SearchDtos;
import com.kt.backendapp.dto.bus.DetailDtos;
import com.kt.backendapp.dto.bus.ArrivalDtos;

import java.util.List;

public interface BusServiceInterface {
    // 검색 조회
    SearchDtos.SearchResponse search(String q);
    
    // 정류장 상세 정보 조회
    DetailDtos.StationDetailResponse getStationDetail(String stationId);
    
    // 정류장 도착 정보 조회
    List<ArrivalDtos.Item> getStationArrivals(String stationId);
    
    // 노선 상세 정보 조회
    DetailDtos.RouteDetailResponse getRouteDetail(String routeId);
    
    // 주변 정류장 검색
    SearchDtos.StationAroundResponse getStationsAround(String x, String y);
}
