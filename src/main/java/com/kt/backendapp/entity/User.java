package com.kt.backendapp.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import java.time.OffsetDateTime;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Table(name = "users", indexes = {
    @Index(name = "ux_users_email", columnList = "email", unique = true)
})
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String email;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(name = "password", nullable = false)
    private String password;

    // 현재 위치 정보 추가
    @Column(name = "current_lat")
    private Double currentLat;  // 현재 위도

    @Column(name = "current_lng")
    private Double currentLng;  // 현재 경도

    @Column(name = "location_updated_at")
    private OffsetDateTime locationUpdatedAt;  // 위치 업데이트 시간

    @CreationTimestamp
    @Column(name = "created_at", nullable = false)
    private OffsetDateTime createdAt;
}