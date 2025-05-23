package com.example.scheduler.user.controller;

import com.example.scheduler.user.service.UserService;
import com.example.scheduler.user.dto.UserRequestDto;
import com.example.scheduler.user.dto.UserResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController // REST API의 컨트롤러임을 명시
@RequiredArgsConstructor // userService 생성자 주입
@RequestMapping("/api/users") // 공통 경로 설정
public class UserController {

    private final UserService userService;

    // 회원가입 API - POST /api/users/signup
    @PostMapping("/signup")
    public UserResponseDto signup(@RequestBody UserRequestDto requestDto) {
        // 요청 body(JSON)를 requestDto로 받고, 회원가입 결과 반환
        return userService.signup(requestDto);
    }
}
