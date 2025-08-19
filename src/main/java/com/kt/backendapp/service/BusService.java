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

    return new SearchDtos.SearchResponse(List.of(), stops);
  }

  /** 정류장 도착 */
  public ArrivalDtos.ListResponse arrivals(String stationId) {
    var items = api.getArrivals(stationId).stream().map(a ->
        new ArrivalDtos.Item(
            a.routeId,
            a.routeName,
            a.predictTime1, // 남은 분
            a.locationNo1,  // 몇 정류장 전
            null,           // lastBus: 문서에 없으면 null
            null            // congestion: 문서에 없으면 null
        )
    ).toList();

    return new ArrivalDtos.ListResponse(
        new ArrivalDtos.StationInfo(stationId, null, null, null, null),
        items
    );
  }
}
