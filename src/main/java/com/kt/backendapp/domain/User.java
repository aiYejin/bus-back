package com.kt.backendapp.domain;

import lombok.*;
import java.time.OffsetDateTime;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class User {
    private Long id;
    private String username;
    private String email;
    private String password;
    private OffsetDateTime createdAt;

    // 비즈니스 로직 메서드들
    public boolean isValidEmail() {
        return email != null && email.contains("@") && email.contains(".");
    }

    public boolean isValidPassword() {
        return password != null && password.length() >= 6;
    }

    public boolean isValidUsername() {
        return username != null && username.length() >= 2 && username.length() <= 50;
    }
}
