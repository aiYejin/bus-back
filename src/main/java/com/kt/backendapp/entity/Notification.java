package com.kt.backendapp.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import java.time.OffsetDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "notifications", indexes = {
    @Index(name = "ix_notif_user", columnList = "user_id"),
    @Index(name = "ix_notif_user_station", columnList = "user_id,station_id")
})
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Notification {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "station_id", nullable = false, length = 64)
    private String stationId;

    @Column(name = "route_id", length = 64)  // 선택사항
    private String routeId;

    @Column(name = "alert_minutes", nullable = false)
    private Integer alertMinutes;

    // 알림 시작 시간 추가
    @Column(name = "start_time")
    private OffsetDateTime startTime;  // 언제부터 알림을 시작할지

    @CreationTimestamp
    @Column(name = "created_at", nullable = false)
    private OffsetDateTime createdAt;

    @JsonProperty("userId")
    public Long getUserIdForJson() {
        return (user != null) ? user.getId() : null;
    }
}