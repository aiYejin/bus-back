package com.kt.backendapp.dto.bus;

import java.util.List;

public final class DetailDtos {
    
    public record RouteDetailResponse(
        String routeId,
        String routeName,
        String startStationName,
        String endStationName,
        String routeTypeName,
        String regionName,
        String adminName,
        String companyName,
        String companyTel,
        String garageName,
        String garageTel,
        String upFirstTime,
        String upLastTime,
        String downFirstTime,
        String downLastTime,
        Integer peekAlloc,
        Integer nPeekAlloc,
        String turnStNm,
        List<StationItem> stations
    ) {}
    
    public record StationItem(
        String stationId,
        String stationName,
        Integer mobileNo,
        Integer stationSeq,
        Double lat,
        Double lng,
        String regionName,
        String adminName,
        String centerYn,
        String turnYn,
        Integer turnSeq
    ) {}

    // 정류장 상세 정보 (도착 정보 포함)
    public record StationDetailResponse(
        String stationId,     // 정류장 ID
        String stationName,   // 정류장명
        Integer mobileNo,     // 정류장 번호
        Double lat,           // 위도
        Double lng,           // 경도
        String regionName,    // 정류장 위치 지역명
        String centerYn,      // 중앙차로 여부 (N:일반,Y:중앙차로)
        List<ArrivalDtos.Item> arrivals  // 도착 정보 포함
    ) {}
}