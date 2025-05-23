package com.example.scheduler.schedule.repository;

import com.example.scheduler.schedule.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

// Schedule 엔티티를 DB와 연결해주는 인터페이스
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
}
