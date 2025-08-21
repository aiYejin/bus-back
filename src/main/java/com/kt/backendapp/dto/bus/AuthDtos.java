package com.kt.backendapp.dto.bus;

import jakarta.validation.constraints.*;

public final class AuthDtos {
    
    // 로그인 요청
    public record LoginRequest(
        @NotBlank(message = "이메일은 필수입니다")
        @Email(message = "올바른 이메일 형식이 아닙니다")
        String email,
        
        @NotBlank(message = "비밀번호는 필수입니다")
        @Size(min = 6, max = 20, message = "비밀번호는 6자 이상 20자 이하여야 합니다")
        String password
    ) {}
    
    // 회원가입 요청
    public record SignupRequest(
        @NotBlank(message = "사용자명은 필수입니다")
        @Size(min = 2, max = 10, message = "사용자명은 2자 이상 10자 이하여야 합니다")
        @Pattern(regexp = "^[가-힣a-zA-Z0-9]+$", message = "사용자명은 한글, 영문, 숫자만 사용 가능합니다")
        String username,
        
        @NotBlank(message = "이메일은 필수입니다")
        @Email(message = "올바른 이메일 형식이 아닙니다")
        String email,
        
        @NotBlank(message = "비밀번호는 필수입니다")
        @Size(min = 6, max = 20, message = "비밀번호는 6자 이상 20자 이하여야 합니다")
        @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*\\d)[a-zA-Z\\d@$!%*?&]+$", 
                message = "비밀번호는 영문과 숫자를 포함해야 합니다")
        String password
    ) {}
    
    // 비밀번호 찾기 요청
    public record FindPasswordRequest(
        @NotBlank(message = "이메일은 필수입니다")
        @Email(message = "올바른 이메일 형식이 아닙니다")
        String email,
        
        @NotBlank(message = "사용자명은 필수입니다")
        @Size(min = 2, max = 10, message = "사용자명은 2자 이상 10자 이하여야 합니다")
        String username
    ) {}
    
    // 로그인/회원가입 응답
    public record AuthResponse(
        Long userId,
        String username,
        String email,
        String createdAt,
        String message
    ) {}

    // 사용자 정보 수정 요청
    public record UpdateUserRequest(
        @NotBlank(message = "사용자명은 필수입니다")
        @Size(min = 2, max = 10, message = "사용자명은 2자 이상 10자 이하여야 합니다")
        @Pattern(regexp = "^[가-힣a-zA-Z0-9]+$", message = "사용자명은 한글, 영문, 숫자만 사용 가능합니다")
        String username,
        
        @NotBlank(message = "이메일은 필수입니다")
        @Email(message = "올바른 이메일 형식이 아닙니다")
        String email
    ) {}

    // 비밀번호 변경 요청
    public record ChangePasswordRequest(
        @NotBlank(message = "현재 비밀번호는 필수입니다")
        String currentPassword,
        
        @NotBlank(message = "새 비밀번호는 필수입니다")
        @Size(min = 6, max = 20, message = "비밀번호는 6자 이상 20자 이하여야 합니다")
        @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*\\d)[a-zA-Z\\d@$!%*?&]+$", 
                message = "비밀번호는 영문과 숫자를 포함해야 합니다")
        String newPassword
    ) {}

    // 계정 삭제 요청
    public record DeleteAccountRequest(
        @NotBlank(message = "비밀번호는 필수입니다")
        String password
    ) {}

    // 일반 응답
    public record MessageResponse(
        String message
    ) {}
}
