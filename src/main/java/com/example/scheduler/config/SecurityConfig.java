package com.example.scheduler.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration // 이 클래스는 스프링 설정 클래스
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                // CSRF 보호 기능을 비활성화
                .csrf((csrf) -> csrf.disable())

                // 요청 권한 설정
                .authorizeHttpRequests((auth) -> auth
                        .requestMatchers("/api/users/**").permitAll()       // 회원가입, 로그인은 인증 없이 허용
                        .requestMatchers("/api/schedules/**").permitAll()   // 일정 API는 임시로 모두 허용 (테스트용)
                        .anyRequest().authenticated()                       // 그 외 모든 요청은 인증 필요
                )

                // 로그인 폼 사용 안 함
                .formLogin((form) -> form.disable())

                // HTTP 기본 인증 비활성화 (브라우저 팝업 로그인 안 쓰기)
                .httpBasic((basic) -> basic.disable());

        return http.build(); // 최종 SecurityFilterChain 반환
    }
}
