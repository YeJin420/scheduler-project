package com.example.scheduler.schedule.controller;

import com.example.scheduler.schedule.dto.ScheduleRequestDto;
import com.example.scheduler.schedule.dto.ScheduleResponseDto;
import com.example.scheduler.schedule.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // REST API 컨트롤러
@RequiredArgsConstructor
@RequestMapping("/api/schedules") // 기본 경로 설정
public class ScheduleController {

    private final ScheduleService scheduleService;

    // 일정 등록 API (POST 요청)
    @PostMapping
    public ScheduleResponseDto create(@RequestBody ScheduleRequestDto requestDto) {
        return scheduleService.createSchedule(requestDto);
    }

    // 전체 일정 조회 API (GET 요청)
    @GetMapping
    public List<ScheduleResponseDto> getAll() {
        return scheduleService.getAllSchedules();
    }
}
