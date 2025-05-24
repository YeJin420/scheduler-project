package com.example.scheduler.schedule.service;

import com.example.scheduler.schedule.repository.ScheduleRepository;
import com.example.scheduler.schedule.dto.ScheduleRequestDto;
import com.example.scheduler.schedule.dto.ScheduleResponseDto;
import com.example.scheduler.schedule.entity.Schedule;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor // final 필드 생성자 자동 생성
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    // 일정 등록 기능
    public ScheduleResponseDto createSchedule(ScheduleRequestDto requestDto) {
        Schedule schedule = new Schedule(
                requestDto.getUsername(),
                requestDto.getTitle(),
                requestDto.getContents()
        );
        Schedule saved = scheduleRepository.save(schedule);

        return new ScheduleResponseDto(
                saved.getId(),
                saved.getUsername(),
                saved.getTitle(),
                saved.getContents(),
                saved.getCreatedAt(),
                saved.getModifiedAt()
        );
    }

    // 전체 일정 조회 기능
    public List<ScheduleResponseDto> getAllSchedules() {
        return scheduleRepository.findAll().stream()
                .map(s -> new ScheduleResponseDto(
                        s.getId(), s.getUsername(), s.getTitle(),
                        s.getContents(), s.getCreatedAt(), s.getModifiedAt()
                ))
                .collect(Collectors.toList());
    }
}
