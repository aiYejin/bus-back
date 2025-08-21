package com.kt.backendapp.domain;

import lombok.*;
import java.time.OffsetDateTime;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class User {
    private Long id;
    private String username;
    private String email;
    private String password;
    private Double currentLat;
    private Double currentLng;
    private OffsetDateTime locationUpdatedAt;
    private OffsetDateTime createdAt;

    // 비즈니스 로직 메서드들
    public boolean isValidEmail() {
        return email != null && email.contains("@") && email.contains(".");
    }

    public boolean hasValidLocation() {
        return currentLat != null && currentLng != null && 
               currentLat >= -90 && currentLat <= 90 &&
               currentLng >= -180 && currentLng <= 180;
    }

    public void updateLocation(Double lat, Double lng) {
        if (lat != null && lng != null && 
            lat >= -90 && lat <= 90 && 
            lng >= -180 && lng <= 180) {
            this.currentLat = lat;
            this.currentLng = lng;
            this.locationUpdatedAt = OffsetDateTime.now();
        }
    }

    public boolean isLocationRecent() {
        if (locationUpdatedAt == null) return false;
        return OffsetDateTime.now().minusMinutes(30).isBefore(locationUpdatedAt);
    }

    public boolean isValidPassword() {
        return password != null && password.length() >= 6;
    }

    public boolean isValidUsername() {
        return username != null && username.length() >= 2 && username.length() <= 50;
    }
}
