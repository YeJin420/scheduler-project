package com.example.scheduler.user.dto;

import lombok.Getter;
import lombok.AllArgsConstructor;

@Getter // getter 자동 생성
@AllArgsConstructor // 모든 필드를 받는 생성자 자동 생성
public class UserResponseDto {
    // 회원가입 완료 후 사용자 정보를 응답할 때 사용하는 클래스
    private Long id;
    private String username;
    private String email;
}
