package com.kt.backendapp.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import java.time.OffsetDateTime;

// ⬇⬇ 추가
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

    // ⬇⬇ JSON 응답에서 user 객체는 제외
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Enumerated(EnumType.STRING)
    @Column(name = "entity_type", nullable = false, length = 10)
    private RefType entityType;

    @Column(name = "ref_id", nullable = false, length = 64)
    private String refId;

    @CreationTimestamp
    @Column(name = "viewed_at", nullable = false)
    private OffsetDateTime viewedAt;

    // ⬇⬇ 필요시 userId만 노출
    @JsonProperty("userId")
    public Long getUserIdForJson() {
        return (user != null) ? user.getId() : null;
    }
}
