package com.example.scheduler.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf((csrf) -> csrf.disable()) // ✅ CSRF 보호 끄기 (람다 방식)
                .authorizeHttpRequests((auth) -> auth
                        .requestMatchers("/api/users/**").permitAll()  // 회원가입/로그인 허용
                        .requestMatchers("/api/schedules/**").permitAll() // (테스트용 허용)
                        .anyRequest().authenticated() // 나머지는 인증 필요
                )
                .formLogin((form) -> form.disable()) // 폼 로그인 비활성화
                .httpBasic((basic) -> basic.disable()); // HTTP 기본 인증 비활성화

        return http.build();
    }
}
