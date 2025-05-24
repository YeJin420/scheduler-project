package com.example.scheduler.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class AuthFilter extends OncePerRequestFilter {

    // 실제 인증 검사 로직을 담은 메서드
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String uri = request.getRequestURI();

        // 로그인 및 회원가입 요청은 필터 통과 (예외 처리)
        if (uri.startsWith("/api/users/login") || uri.startsWith("/api/users/signup")) {
            filterChain.doFilter(request, response);
            return;
        }

        // 세션이 없거나 로그인 정보가 없으면 401 반환
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("username") == null) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("로그인이 필요합니다.");
            return;
        }

        // 세션이 있다면 다음 필터 또는 컨트롤러로 넘어감
        filterChain.doFilter(request, response);
    }
}
