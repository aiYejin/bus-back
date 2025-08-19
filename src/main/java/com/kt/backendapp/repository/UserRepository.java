package com.kt.backendapp.repository;

import com.kt.backendapp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    Optional<User> findByEmailAndPassword(String email, String password); // mock-login용
    boolean existsByEmail(String email);
}
