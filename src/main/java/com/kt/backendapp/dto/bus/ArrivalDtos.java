// src/main/java/com/kt/backendapp/dto/bus/ArrivalDtos.java
package com.kt.backendapp.dto.bus;

import java.util.List;

/** 정류장 도착정보 응답 DTO 묶음 */
public final class ArrivalDtos {

    /** 정류장 메타 (이름/ARS/좌표는 추후 보강 가능) */
    public record StationInfo(
        String stationId,
        String stationName,
        String arsId,
        Double lat,
        Double lng
    ) {}

    /** 한 노선의 도착 정보 */
    public record Item(
        String routeId,
        String routeName,
        Integer remainMin,    // 남은 분
        Integer remainStops,  // 몇 정류장 전
        Boolean lastBus,      // 막차 여부(정보 없으면 null)
        String congestion     // 혼잡도(없으면 null)
    ) {}

    /** 전체 응답 */
    public record ListResponse(
        StationInfo station,
        List<Item> arrivals
    ) {}
}
