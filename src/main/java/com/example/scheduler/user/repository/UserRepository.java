package com.example.scheduler.user.repository;

import com.example.scheduler.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


// interface
public interface UserRepository extends JpaRepository<User, Long> {
    // 기본적인 CRUD는 JpaRepository가 제공
    // 여기에 username으로 사용자 찾는 커스텀 메서드 추가
    Optional<User> findByUsername(String username);
}
