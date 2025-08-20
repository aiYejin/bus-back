// web/BusController.java
package com.kt.backendapp.web;

import com.kt.backendapp.dto.bus.ArrivalDtos;
import com.kt.backendapp.dto.bus.SearchDtos;
import com.kt.backendapp.dto.bus.DetailDtos;
import com.kt.backendapp.service.BusService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class BusController {
  private final BusService service;

  @GetMapping(value="/search", produces=MediaType.APPLICATION_JSON_VALUE)
  public SearchDtos.SearchResponse search(@RequestParam String q) {
    return service.search(q);
  }

  @GetMapping(value="/stations/{stationId}/detail", produces=MediaType.APPLICATION_JSON_VALUE)
  public DetailDtos.StationDetailResponse getStationDetail(@PathVariable String stationId) {
      return service.getStationDetail(stationId);
  }

  @GetMapping(value="/routes/{routeId}/detail", produces=MediaType.APPLICATION_JSON_VALUE)
  public DetailDtos.RouteDetailResponse getRouteDetail(@PathVariable String routeId) {
      return service.getRouteDetail(routeId);
  }
}
