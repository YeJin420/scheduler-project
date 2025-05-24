package com.example.scheduler.user.controller;


import com.example.scheduler.user.dto.LoginRequestDto;
import com.example.scheduler.user.service.UserService;
import com.example.scheduler.user.dto.UserRequestDto;
import com.example.scheduler.user.dto.UserResponseDto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

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
        return ResponseEntity.ok("회원가입 성공!");
    }

    // 로그인 API - POST/api/users/login
    @PostMapping("/login")
    public void login(@RequestBody LoginRequestDto requestDto, HttpServletRequest request, HttpServletResponse response)
            // 이 메서드 안에서 IOException이 발생할 수 있으니 호출한 쪽에서 처리하거나 외부로 던진다
            // 내가 직접 처리 안할테니 나중에 처리해줘 라는 뜻
            throws IOException {
            try {
                // 로그인 처리
                userService.login(requestDto, request);

                // 상태 코드 설정 (200 OK)
                response.setStatus(HttpServletResponse.SC_OK);

                // 응답 메시지 직접 작성
                response.setContentType("text/plain;charset=UTF-8");
                response.getWriter().write("로그인 성공!");
                } catch (IllegalArgumentException e) {

                // 실패한 경우 401 Unauthorized
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.setContentType("text/plain;charset=UTF-8");
                response.getWriter().write(e.getMessage());
            }
        }
}

// try-catch 문으로 리팩토링 한 부분
//
// public class UserController {
//
//    private final UserService userService;
//
//    // 회원가입 API
//    @PostMapping("/signup")
//    public ResponseEntity<String> signup(@RequestBody UserRequestDto requestDto) {
//        try {
//            userService.signup(requestDto);
//            return ResponseEntity.ok("회원가입 성공!");
//        } catch (IllegalArgumentException e) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
//        }
//    }
//
//    // 로그인 API
//    @PostMapping("/login")
//    public ResponseEntity<String> login(@RequestBody LoginRequestDto requestDto, HttpServletRequest request) {
//        try {
//            userService.login(requestDto, request);
//            return ResponseEntity.ok("로그인 성공!");
//        } catch (IllegalArgumentException e) {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
//        }
//    }