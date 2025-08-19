// web/BusController.java
package com.kt.backendapp.web;

import com.kt.backendapp.dto.bus.ArrivalDtos;
import com.kt.backendapp.dto.bus.SearchDtos;
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
    System.out.println("-----------------------------------");
    return service.search(q);
  }

  @GetMapping(value="/stops/{stationId}/arrivals", produces=MediaType.APPLICATION_JSON_VALUE)
  public ArrivalDtos.ListResponse arrivals(@PathVariable String stationId) {
    return service.arrivals(stationId);
  }
}
