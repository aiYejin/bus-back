package com.kt.backendapp.client.gbis;

import com.kt.backendapp.config.GbisOpenApiProps;
import com.kt.backendapp.dto.gbis.StationsRes;
import com.kt.backendapp.dto.gbis.ArrivalRes;
import com.kt.backendapp.dto.gbis.RoutesRes;
import com.kt.backendapp.dto.gbis.RouteRes;
import com.kt.backendapp.dto.gbis.RouteStationRes;
import com.kt.backendapp.dto.gbis.RouteLineRes;
import com.kt.backendapp.dto.gbis.StationRes;
import com.kt.backendapp.dto.gbis.StationAroundRes;

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

    // 노선 리스트 Get API
    public List<RoutesRes.Response.MsgBody.BusRouteList> getRoutes(String keyword) {
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
            RoutesRes res = mapper.readValue(response.body(), RoutesRes.class);
            
            return (res != null && res.response != null && res.response.msgBody != null && 
                    res.response.msgBody.busRouteList != null) ? res.response.msgBody.busRouteList : List.of();
                    
        } catch (IOException | InterruptedException e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
            return List.of();
        }
    }
    
    // 정류장 리스트 Get API
    public List<StationsRes.Response.MsgBody.BusStationList> getStations(String keyword) {
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
            StationsRes res = mapper.readValue(response.body(), StationsRes.class);
            
            return (res != null && res.response != null && res.response.msgBody != null && 
                    res.response.msgBody.busStationList != null) ? res.response.msgBody.busStationList : List.of();
                    
        } catch (IOException | InterruptedException e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
            return List.of();
        }
    }

    // 정류장 도착 정보 Get API
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

    // 노선 상세 정보 API
    public RouteRes.Response.MsgBody.BusRouteInfoItem getRoute(String routeId) {
        String url = "https://apis.data.go.kr/6410000/busrouteservice/v2/getBusRouteInfoItemv2"
                + "?format=json"
                + "&serviceKey=" + getEncodedServiceKey()
                + "&routeId=" + routeId;

        System.out.println("Route URL: " + url);
        
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
            
            if (res != null && res.response != null && res.response.msgBody != null && 
                res.response.msgBody.busRouteInfoItem != null) {
                return res.response.msgBody.busRouteInfoItem;
            }
            return null;
                    
        } catch (IOException | InterruptedException e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    // 노선별 정류장 목록 API
    public List<RouteStationRes.Response.MsgBody.BusRouteStationList> getRouteStations(String routeId) {
        String url = "https://apis.data.go.kr/6410000/busrouteservice/v2/getBusRouteStationListv2"
                + "?format=json"
                + "&serviceKey=" + getEncodedServiceKey()
                + "&routeId=" + routeId;

        System.out.println("Route Station List URL: " + url);
        
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
            RouteStationRes res = mapper.readValue(response.body(), RouteStationRes.class);
            
            return (res != null && res.response != null && res.response.msgBody != null && 
                    res.response.msgBody.busRouteStationList != null) ? res.response.msgBody.busRouteStationList : List.of();
                    
        } catch (IOException | InterruptedException e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
            return List.of();
        }
    }

    // 정류장 상세 정보 API
    public StationRes.Response.MsgBody.BusStationInfo getStationDetail(String stationId) {
        String url = "https://apis.data.go.kr/6410000/busstationservice/v2/busStationInfov2"
                + "?format=json"
                + "&serviceKey=" + getEncodedServiceKey()
                + "&stationId=" + stationId;

        System.out.println("Station Detail URL: " + url);
        
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
            
            if (res != null && res.response != null && res.response.msgBody != null && 
                res.response.msgBody.busStationInfo != null) {
                return res.response.msgBody.busStationInfo;
            }
            return null;
                    
        } catch (IOException | InterruptedException e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    // 주변 정류장 목록 API
    public List<StationAroundRes.Response.MsgBody.BusStationAroundList> getStationsAround(String x, String y) {
        String url = "https://apis.data.go.kr/6410000/busstationservice/v2/getBusStationAroundListv2"
                + "?format=json"
                + "&serviceKey=" + getEncodedServiceKey()
                + "&x=" + x
                + "&y=" + y;

        System.out.println("Station Around URL: " + url);
        
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
            StationAroundRes res = mapper.readValue(response.body(), StationAroundRes.class);
            
            return (res != null && res.response != null && res.response.msgBody != null && 
                    res.response.msgBody.busStationAroundList != null) ? res.response.msgBody.busStationAroundList : List.of();
                    
        } catch (IOException | InterruptedException e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
            return List.of();
        }
    }

    // 노선별 형상 정보 API
    public List<RouteLineRes.Response.MsgBody.BusRouteLineList> getRouteLines(String routeId) {
        String url = "https://apis.data.go.kr/6410000/busrouteservice/v2/getBusRouteLineListv2"
                + "?format=json"
                + "&serviceKey=" + getEncodedServiceKey()
                + "&routeId=" + routeId;

        System.out.println("Route Line List URL: " + url);
        
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
            RouteLineRes res = mapper.readValue(response.body(), RouteLineRes.class);
            
            return (res != null && res.response != null && res.response.msgBody != null && 
                    res.response.msgBody.busRouteLineList != null) ? res.response.msgBody.busRouteLineList : List.of();
                    
        } catch (IOException | InterruptedException e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
            return List.of();
        }
    }
}