package com.kt.backendapp.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import java.time.OffsetDateTime;

// 추가
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) // 프록시 속성 무시
@Entity
@Table(name = "users", indexes = {
    @Index(name = "ux_users_email", columnList = "email", unique = true)
})
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)  // username 추가 (중복 가능)
    private String username;

    @Column(nullable = false)
    private String email;

    // 응답에서 비밀번호는 숨기기(입력은 받음)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(name = "password", nullable = false)  // NOT NULL로 변경
    private String password;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false)
    private OffsetDateTime createdAt;
}