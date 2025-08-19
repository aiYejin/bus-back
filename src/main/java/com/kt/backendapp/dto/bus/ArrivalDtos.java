package com.kt.backendapp.dto.bus;

import java.util.List;

/** 정류장 도착정보 응답 DTO 묶음 */
public final class ArrivalDtos {

    /** 한 노선의 도착 정보 */
    public record Item(
        String routeId,
        String routeName,
        // 첫번째 차량
        Integer remainMin1,     // 첫번째 차량 남은 분
        Integer remainStops1,   // 첫번째 차량 몇 정류장 전
        String flag1,           // 첫번째 차량 상태구분
        String congestion1,     // 첫번째 차량 혼잡도
        String plateNo1,        // 첫번째 차량 번호
        // 두번째 차량
        Integer remainMin2,     // 두번째 차량 남은 분
        Integer remainStops2,   // 두번째 차량 몇 정류장 전
        String flag2,           // 두번째 차량 상태구분
        String congestion2,     // 두번째 차량 혼잡도
        String plateNo2         // 두번째 차량 번호
    ) {}

    /** 전체 응답 */
    public record ListResponse(
        List<Item> arrivals
    ) {}
}