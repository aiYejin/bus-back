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
}