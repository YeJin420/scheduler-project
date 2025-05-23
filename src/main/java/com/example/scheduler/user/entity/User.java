package com.example.scheduler.user.entity;

import com.example.scheduler.common.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity // 이 클래스가 JPA에서 관리하는 DB 테이블이라는 의미
@Getter  // 모든 필드에 대해 getter 메서드를 자동 생성해줌
@NoArgsConstructor // 기본 생성자 자동 생성
public class User extends BaseEntity{

    @Id // 기본 키(Primary Key) 지정
    @GeneratedValue(strategy = GenerationType.IDENTITY) // AUTO_INCREMENT처럼 자동 증가
    private Long id;

    @Column(nullable = false, unique = true) // null 금지 + 중복 금지
    private String username;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false) // null 금지
    private String password;

    // 직접 만든 생성자 (username, password를 한 번에 받을 때 사용)
    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }
}
