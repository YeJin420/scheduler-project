package com.example.scheduler.user.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable() // POST 요청 허용
                .authorizeHttpRequests((auth) -> auth
                        .requestMatchers("/api/users/**").permitAll() // 회원가입, 로그인은 인증 없이 허용
                        .requestMatchers("/api/schedules/**").permitAll() // 테스트용으로 임시로 허용 (나중에 인증 추가 예정)
                        .anyRequest().authenticated() // 나머지는 인증 필요
                )
                .formLogin().disable() // 로그인 form 비활성화
                .httpBasic().disable(); // HTTP 기본 인증도 비활성화

        return http.build();
    }
}
