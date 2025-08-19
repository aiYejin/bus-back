package com.kt.backendapp.client;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

/**
 * 공통 HTTP API 호출 클라이언트
 * 모든 외부 API 호출에 사용되는 공통 로직
 */
@Slf4j
@Component
public class HttpApiClient {
    private final HttpClient client = HttpClient.newHttpClient();
    
    /**
     * GET 요청을 보내고 응답 본문을 반환
     * @param url 요청할 URL
     * @return 응답 본문 문자열
     */
    public String get(String url) {
        log.info("API Request URL: {}", url);
        
        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(url))
            .header("Accept", "application/json")
            .GET()
            .build();
            
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            
            log.info("API Response Status: {}", response.statusCode());
            log.info("API Response Body: {}", response.body());
            
            return response.body();
        } catch (IOException | InterruptedException e) {
            log.error("API 호출 실패: {}", e.getMessage(), e);
            throw new RuntimeException("API 호출 실패: " + e.getMessage(), e);
        }
    }
}