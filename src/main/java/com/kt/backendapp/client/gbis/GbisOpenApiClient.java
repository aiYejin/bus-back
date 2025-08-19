package com.kt.backendapp.client.gbis;

import com.kt.backendapp.config.GbisOpenApiProps;
import com.kt.backendapp.dto.gbis.StationRes;
import com.kt.backendapp.dto.gbis.ArrivalRes;
import com.kt.backendapp.dto.gbis.RouteRes;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.io.IOException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class GbisOpenApiClient {
    private final GbisOpenApiProps props;

    // 인코딩된 서비스 키 생성
    private String getEncodedServiceKey() {
        String serviceKey = props.key();
        return URLEncoder.encode(serviceKey, StandardCharsets.UTF_8);
    }

    // 노선 검색 API
    public List<RouteRes.Response.MsgBody.BusRouteList> searchRoutes(String keyword) {
        String url = "https://apis.data.go.kr/6410000/busrouteservice/v2/getBusRouteListv2"
                + "?format=json"
                + "&serviceKey=" + getEncodedServiceKey()
                + "&keyword=" + keyword;

        System.out.println("Route Search URL: " + url);
        
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Accept", "application/json")
                .GET()
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            
            System.out.println("Status Code: " + response.statusCode());
            System.out.println("Response Body:\n" + response.body());
            
            ObjectMapper mapper = new ObjectMapper();
            RouteRes res = mapper.readValue(response.body(), RouteRes.class);
            
            return (res != null && res.response != null && res.response.msgBody != null && 
                    res.response.msgBody.busRouteList != null) ? res.response.msgBody.busRouteList : List.of();
                    
        } catch (IOException | InterruptedException e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
            return List.of();
        }
    }
    
    // 정류장 검색 API
    public List<StationRes.Response.MsgBody.BusStationList> searchStations(String keyword) {
        String url = "https://apis.data.go.kr/6410000/busstationservice/v2/getBusStationListv2"
                + "?format=json"
                + "&serviceKey=" + getEncodedServiceKey()
                + "&keyword=" + keyword;

        System.out.println("Station Search URL: " + url);
        
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Accept", "application/json")
                .GET()
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            
            System.out.println("Status Code: " + response.statusCode());
            System.out.println("Response Body:\n" + response.body());
            
            ObjectMapper mapper = new ObjectMapper();
            StationRes res = mapper.readValue(response.body(), StationRes.class);
            
            return (res != null && res.response != null && res.response.msgBody != null && 
                    res.response.msgBody.busStationList != null) ? res.response.msgBody.busStationList : List.of();
                    
        } catch (IOException | InterruptedException e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
            return List.of();
        }
    }

    // 정류장 도착 정보 API
    public List<ArrivalRes.Response.MsgBody.BusArrivalList> getArrivals(String stationId) {
        String url = "https://apis.data.go.kr/6410000/busarrivalservice/v2/getBusArrivalListv2"
                + "?format=json"
                + "&serviceKey=" + getEncodedServiceKey()
                + "&stationId=" + stationId;

        System.out.println("Arrival Search URL: " + url);
        
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Accept", "application/json")
                .GET()
                .build();
        
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            
            System.out.println("Status Code: " + response.statusCode());
            System.out.println("Response Body:\n" + response.body());
            
            ObjectMapper mapper = new ObjectMapper();
            ArrivalRes res = mapper.readValue(response.body(), ArrivalRes.class);
            
            return (res != null && res.response != null && res.response.msgBody != null && 
                    res.response.msgBody.busArrivalList != null) ? res.response.msgBody.busArrivalList : List.of();
                    
        } catch (IOException | InterruptedException e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
            return List.of();
        }
    }
}