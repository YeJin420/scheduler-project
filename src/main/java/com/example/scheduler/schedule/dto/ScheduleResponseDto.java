package com.example.scheduler.schedule.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor // 전체 필드를 받는 생성자 자동 생성
public class ScheduleResponseDto {
    // 클라이언트에게 보낼 응답 데이터
    private Long id;
    private String username;
    private String title;
    private String contents;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
}
