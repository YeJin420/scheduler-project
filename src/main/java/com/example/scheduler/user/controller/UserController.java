package com.example.scheduler.user.controller;


import com.example.scheduler.user.dto.LoginRequestDto;
import com.example.scheduler.user.service.UserService;
import com.example.scheduler.user.dto.UserRequestDto;
import com.example.scheduler.user.dto.UserResponseDto;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController // REST API의 컨트롤러임을 명시
@RequiredArgsConstructor // userService 생성자 주입
@RequestMapping("/api/users") // 공통 경로 설정
public class UserController {

    private final UserService userService;

    // 회원가입 API - POST/api/users/signup
    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody UserRequestDto requestDto) {
        userService.signup(requestDto);
        // 요청 body(JSON)를 requestDto로 받고, 회원가입 결과 반환
        return ResponseEntity.ok("회원가입 성공");
    }

    // 로그인 API - POST/api/users/login
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequestDto requestDto, HttpServletRequest request) {
        try { userService.login(requestDto, request); // 로그인 시도
            return ResponseEntity.ok("로그인 성공!");
        } catch (IllegalArgumentException e) {
            // 예외 발생 시 HTTP 401 Unauthorized 반환, 인증 실패를 명확하게 전달해줌
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }


}
