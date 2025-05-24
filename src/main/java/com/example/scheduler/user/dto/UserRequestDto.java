package com.example.scheduler.user.dto;

import lombok.Getter;

@Getter // 자동으로 getter 생성
public class UserRequestDto {

    // 사용자가 보내는 회원가입 요청 데이터를 담는 클래스
    private String username;
    private String email;
    private String password;

}
