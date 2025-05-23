package com.example.scheduler.schedule.dto;

import lombok.Getter;

@Getter
public class ScheduleRequestDto {
    // 클라이언트가 보낸 일정 등록 요청
    private String username;
    private String title;
    private String contents;
}
