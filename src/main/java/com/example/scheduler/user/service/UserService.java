package com.example.scheduler.user.service;

import com.example.scheduler.user.entity.User;
import com.example.scheduler.user.repository.UserRepository;
import com.example.scheduler.user.dto.UserRequestDto;
import com.example.scheduler.user.dto.UserResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service // 서비스 계층임을 명시
@RequiredArgsConstructor // final 필드인 userRepository를 생성자 주입해줌
public class UserService {

    private final UserRepository userRepository;

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
}
