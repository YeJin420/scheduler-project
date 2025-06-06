package com.example.scheduler.user.dto;

import lombok.Getter;
import com.example.scheduler.user.entity.User;
//import lombok.AllArgsConstructor;

@Getter // getter 자동 생성
public class UserResponseDto {

    // 회원가입 완료 후 사용자 정보를 응답할 때 사용하는 클래스
    private Long id;
    private String username;
    private String email;

    // Entity → DTO로 변환하는 생성자
    public UserResponseDto(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.email = user.getEmail();
    }

}
