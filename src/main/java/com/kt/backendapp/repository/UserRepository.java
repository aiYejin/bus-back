package com.kt.backendapp.repository;

import com.kt.backendapp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmailAndPassword(String email, String password); // 로그인용
    Optional<User> findByEmailAndUsername(String email, String username); // 비밀번호 찾기용
    List<User> findByEmail(String email); // 이메일로 조회 (디버깅용)
    List<User> findByUsername(String username); // 사용자명으로 조회 (디버깅용)
    boolean existsByEmail(String email); // 이메일 중복 체크용
}