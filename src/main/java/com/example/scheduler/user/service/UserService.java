package com.example.scheduler.user.service;

import com.example.scheduler.user.dto.LoginRequestDto;
import com.example.scheduler.user.dto.UserRequestDto;
import com.example.scheduler.user.dto.UserResponseDto;
import com.example.scheduler.user.entity.User;
import com.example.scheduler.user.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service // 서비스 계층임을 명시
@RequiredArgsConstructor // final 필드인 userRepository를 생성자 주입해줌
public class UserService {

    // 사용자 정보를 처리하기 위한 저장소 (DB와 연결됨)
    private final UserRepository userRepository;

    /**
    // 회원가입 로직
    public UserResponseDto signup(UserRequestDto requestDto) {
        // 요청으로 받은 username, password로 User 객체 생성
        User user = new User(
                requestDto.getUsername(),
                requestDto.getPassword(),
                requestDto.getEmail()
        );

     // DB에 저장
     User saved = userRepository.save(user);

     // 저장된 결과를 UserResponseDto로 감싸서 반환
     return new UserResponseDto(saved.getId(), saved.getUsername(), saved.getEmail());

     }

     */

    // 회원가입 기능
    public UserResponseDto signup(UserRequestDto requestDto) {

        String username = requestDto.getUsername();
        String password = requestDto.getPassword();
        String email = requestDto.getEmail();

        // 사용자 이름 중복 검사
        if (userRepository.findByUsername(username).isPresent()) {
            throw new IllegalArgumentException("이미 존재하는 사용자 이름입니다.");
        }

        // 이메일 중복 검사
        if (userRepository.findByEmail(email).isPresent()) {
            throw new IllegalArgumentException("이미 사용 중인 이메일입니다.");
        }

        // 사용자 정보로 User 객체 생성
        User user = new User(username, password, email);

        // DB에 저장 후 반환값 저장
        User savedUser = userRepository.save(user);

        // 저장된 사용자 정보를 응답 DTO로 변환하여 반환
        return new UserResponseDto(savedUser);
    }

    // 로그인 기능
    public void login(LoginRequestDto requestDto, HttpServletRequest request) {
        // 4단계 하다보니 username말고 email이 필요하다고 느낌..ㅎ
        String email = requestDto.getEmail();
        String password = requestDto.getPassword();

        // 입력값이 모두 채워져 있는지 확인(빈 값 방지)
        if (email == null || email.isBlank() || password == null || password.isBlank()) {
            throw new IllegalArgumentException("이메일과 비밀번호를 모두 입력해야 합니다.");
        }

        // 이메일로 사용자 찾기, 없으면 예외
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("등록되지 않은 이메일입니다."));

        // 비밀번호 불일치 예외
        if (!user.getPassword().equals(password)) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        // 로그인 성공하면 세션에 사용자 정보 저장
        HttpSession session = request.getSession(); // 기존 세션 없으면 새로 만듦
        session.setAttribute("username", user.getUsername()); // 사용자 이름 저장
    }
}

// 아래처럼 리팩토링 해야함. 너무 긴 코드라 유지보수 힘듦

// @Service
//@RequiredArgsConstructor
//public class UserService {
//
//    private final UserRepository userRepository;
//
//    // 회원가입
//    public UserResponseDto signup(UserRequestDto requestDto) {
//        // 중복 username 체크
//        if (userRepository.findByUsername(requestDto.getUsername()).isPresent()) {
//            throw new IllegalArgumentException("이미 존재하는 사용자 이름입니다.");
//        }
//
//        // 중복 email 체크
//        if (userRepository.findByEmail(requestDto.getEmail()).isPresent()) {
//            throw new IllegalArgumentException("이미 등록된 이메일입니다.");
//        }
//
//        // 유저 저장
//        User user = new User(requestDto.getUsername(), requestDto.getPassword(), requestDto.getEmail());
//        userRepository.save(user);
//
//        return new UserResponseDto(user);
//    }
//
//    // 로그인
//    public UserResponseDto login(LoginRequestDto requestDto, HttpServletRequest request) {
//        // 이메일 존재 여부 확인
//        User user = userRepository.findByEmail(requestDto.getEmail())
//                .orElseThrow(() -> new IllegalArgumentException("등록되지 않은 이메일입니다."));
//
//        // 비밀번호 확인
//        if (!user.getPassword().equals(requestDto.getPassword())) {
//            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
//        }
//
//        // 세션에 유저 정보 저장
//        request.getSession().setAttribute("user", user);
//        return new UserResponseDto(user);
//    }
//}