package com.kt.backendapp.service;

import com.kt.backendapp.dto.bus.AuthDtos;
import com.kt.backendapp.entity.User;
import com.kt.backendapp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository repository;

    // 로그인
    public AuthDtos.AuthResponse login(AuthDtos.LoginRequest request) {
        User user = repository.findByEmailAndPassword(request.email(), request.password())
                .orElseThrow(() -> new IllegalArgumentException("이메일 또는 비밀번호가 올바르지 않습니다."));
        
        return new AuthDtos.AuthResponse(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getCreatedAt().toString(),
                "로그인 성공"
        );
    }

    // 회원가입
    @Transactional
    public AuthDtos.AuthResponse signup(AuthDtos.SignupRequest request) {
        // 이메일 중복 체크
        if (repository.existsByEmail(request.email())) {
            throw new IllegalArgumentException("이미 사용 중인 이메일입니다.");
        }

        User user = User.builder()
                .username(request.username())
                .email(request.email())
                .password(request.password())
                .build();

        User saved = repository.save(user);
        
        return new AuthDtos.AuthResponse(
                saved.getId(),
                saved.getUsername(),
                saved.getEmail(),
                saved.getCreatedAt().toString(),
                "회원가입 성공"
        );
    }

    // 비밀번호 찾기
    public AuthDtos.MessageResponse findPassword(AuthDtos.FindPasswordRequest request) {
        // 입력값 정리 (공백 제거, 소문자 변환)
        String email = request.email().trim().toLowerCase();
        String username = request.username().trim();
        
        log.info("비밀번호 찾기 요청 - 원본 이메일: '{}', 원본 사용자명: '{}'", request.email(), request.username());
        log.info("비밀번호 찾기 요청 - 정리된 이메일: '{}', 정리된 사용자명: '{}'", email, username);
        
        // 이메일과 사용자명으로 사용자 확인
        try {
            // 먼저 이메일로만 사용자 찾기
            List<User> usersByEmail = repository.findByEmail(email);
            log.info("이메일 '{}'로 찾은 사용자 수: {}", email, usersByEmail.size());
            
            // 사용자명으로만 사용자 찾기
            List<User> usersByUsername = repository.findByUsername(username);
            log.info("사용자명 '{}'로 찾은 사용자 수: {}", username, usersByUsername.size());
            
            // 대소문자 구분 없이 검색을 위해 모든 사용자를 가져와서 필터링
            List<User> allUsers = repository.findAll();
            log.info("전체 사용자 수: {}", allUsers.size());
            
            User foundUser = null;
            for (User user : allUsers) {
                if (user.getEmail().toLowerCase().equals(email) && 
                    user.getUsername().equals(username)) {
                    foundUser = user;
                    break;
                }
            }
            
            if (foundUser == null) {
                throw new IllegalArgumentException("해당 정보로 가입된 계정이 없습니다.");
            }
            
            log.info("사용자 찾음 - ID: {}, 이메일: '{}', 사용자명: '{}'", 
                foundUser.getId(), foundUser.getEmail(), foundUser.getUsername());
            
            // 실제로는 여기서 이메일 발송 로직을 구현해야 하지만,
            // 요구사항에 따라 단순히 메시지만 반환
            return new AuthDtos.MessageResponse("입력하신 이메일로 비밀번호 재설정 안내를 발송했습니다.");
        } catch (Exception e) {
            log.error("비밀번호 찾기 실패 - 이메일: '{}', 사용자명: '{}', 에러: {}", email, username, e.getMessage());
            throw e;
        }
    }

    // 디버깅용: 모든 사용자 조회
    public AuthDtos.MessageResponse getAllUsers() {
        List<User> users = repository.findAll();
        StringBuilder message = new StringBuilder("전체 사용자 목록:\n");
        for (User user : users) {
            message.append(String.format("ID: %d, 이메일: %s, 사용자명: %s\n", 
                user.getId(), user.getEmail(), user.getUsername()));
        }
        log.info("전체 사용자 수: {}", users.size());
        return new AuthDtos.MessageResponse(message.toString());
    }

    // 사용자 정보 수정
    @Transactional
    public AuthDtos.MessageResponse updateUser(Long userId, AuthDtos.UpdateUserRequest request) {
        User user = repository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));

        // 이메일 중복 체크 (자신의 이메일이 아닌 경우)
        if (!user.getEmail().equals(request.email()) && repository.existsByEmail(request.email())) {
            throw new IllegalArgumentException("이미 사용 중인 이메일입니다.");
        }

        user.setUsername(request.username());
        user.setEmail(request.email());
        repository.save(user);

        return new AuthDtos.MessageResponse("사용자 정보가 수정되었습니다.");
    }

    // 비밀번호 변경
    @Transactional
    public AuthDtos.MessageResponse changePassword(Long userId, AuthDtos.ChangePasswordRequest request) {
        log.info("비밀번호 변경 요청 - userId: {}, currentPassword: {}, newPassword: {}", 
                userId, request.currentPassword(), request.newPassword());
        
        User user = repository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));

        log.info("사용자 찾음 - ID: {}, 저장된 비밀번호: {}", user.getId(), user.getPassword());

        // 현재 비밀번호 확인
        if (!user.getPassword().equals(request.currentPassword())) {
            log.error("비밀번호 불일치 - 입력된 비밀번호: {}, 저장된 비밀번호: {}", 
                    request.currentPassword(), user.getPassword());
            throw new IllegalArgumentException("현재 비밀번호가 올바르지 않습니다.");
        }

        user.setPassword(request.newPassword());
        repository.save(user);

        log.info("비밀번호 변경 완료 - userId: {}", userId);
        return new AuthDtos.MessageResponse("비밀번호가 변경되었습니다.");
    }

    // 계정 삭제
    @Transactional
    public AuthDtos.MessageResponse deleteAccount(Long userId, AuthDtos.DeleteAccountRequest request) {
        User user = repository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));

        // 비밀번호 확인
        if (!user.getPassword().equals(request.password())) {
            throw new IllegalArgumentException("비밀번호가 올바르지 않습니다.");
        }

        repository.delete(user);

        return new AuthDtos.MessageResponse("계정이 삭제되었습니다.");
    }
}