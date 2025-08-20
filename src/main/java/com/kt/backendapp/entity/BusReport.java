package com.kt.backendapp.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import java.time.OffsetDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "bus_reports")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class BusReport {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "title", nullable = false, length = 200)
    private String title;  // 신고 제목

    @Column(name = "content", nullable = false, length = 1000)
    private String content;  // 신고 내용

    @Column(name = "email", nullable = false, length = 100)
    private String email;  // 회신 받을 이메일

    @Enumerated(EnumType.STRING)
    @Column(name = "report_type", nullable = false, length = 50)
    private ReportType reportType;  // 신고 유형

    @CreationTimestamp
    @Column(name = "created_at", nullable = false)
    private OffsetDateTime createdAt;

    // JSON 응답에 userId 포함
    @JsonProperty("userId")
    public Long getUserIdForJson() {
        return (user != null) ? user.getId() : null;
    }
}