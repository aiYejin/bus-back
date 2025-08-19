package com.kt.backendapp.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import java.time.OffsetDateTime;

// ⬇⬇ 추가
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(
    name = "favorites",
    uniqueConstraints = {
        @UniqueConstraint(name = "ux_fav_user_type_ref",
            columnNames = {"user_id", "type", "ref_id"})
    },
    indexes = {
        @Index(name = "ix_fav_user", columnList = "user_id"),
        @Index(name = "ix_fav_user_created", columnList = "user_id,created_at"),
        @Index(name = "ix_fav_user_type_created", columnList = "user_id,type,created_at")
    }
)
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Favorite {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // ⬇⬇ JSON 응답에서 user 객체는 제외
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 10)   // STOP | ROUTE
    private RefType type;

    @Column(name = "ref_id", nullable = false, length = 64)
    private String refId;

    @Column(length = 100)
    private String alias;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false)
    private OffsetDateTime createdAt;

    // ⬇⬇ 응답에 userId를 포함하고 싶으면 이 메서드 추가
    @JsonProperty("userId")
    public Long getUserIdForJson() {
        return (user != null) ? user.getId() : null;
    }
}
