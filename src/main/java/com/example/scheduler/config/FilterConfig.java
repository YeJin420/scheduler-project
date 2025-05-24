package com.example.scheduler.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration // 설정 클래스임을 명시
public class FilterConfig {

    @Bean
    public FilterRegistrationBean<AuthFilter> authFilter() {
        FilterRegistrationBean<AuthFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new AuthFilter()); // 우리가 만든 인증 필터 등록
        registrationBean.addUrlPatterns("/api/schedules/*"); // 일정 관련 API에만 필터 적용
        return registrationBean;
    }
}
