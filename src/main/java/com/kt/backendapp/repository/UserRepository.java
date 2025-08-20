package com.kt.backendapp.repository;

import com.kt.backendapp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmailAndPassword(String email, String password); // 로그인용
    boolean existsByEmail(String email); // 이메일 중복 체크용
}