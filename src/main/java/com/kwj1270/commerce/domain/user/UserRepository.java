package com.kwj1270.commerce.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email); // 이메일을 기준으로 찾음
    Optional<User> findByIdAndPassword(Long id, String password);
}
