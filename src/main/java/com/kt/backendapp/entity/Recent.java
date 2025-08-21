package com.kt.backendapp.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.OffsetDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "recents", indexes = {
    @Index(name = "ix_recent_user", columnList = "user_id"),
    @Index(name = "ix_recent_user_type_time", columnList = "user_id,entity_type,viewed_at")
})
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Recent {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Enumerated(EnumType.STRING)
    @Column(name = "entity_type", nullable = false, length = 10)
    private RefType entityType;

    @Column(name = "ref_id", nullable = false, length = 64)
    private String refId;

    @Column(name = "ref_name", nullable = false, length = 100)
    private String refName;  // 노선명 또는 정류장명

    @Column(name = "additional_info", length = 200)
    private String additionalInfo;  // "강남역 ↔ 서초역" 또는 "02301"

    @Column(name = "viewed_at", nullable = false)
    private OffsetDateTime viewedAt;

    @PrePersist
    protected void onCreate() {
        if (viewedAt == null) {
            viewedAt = OffsetDateTime.now();
        }
    }

    @JsonProperty("userId")
    public Long getUserIdForJson() {
        return (user != null) ? user.getId() : null;
    }
}