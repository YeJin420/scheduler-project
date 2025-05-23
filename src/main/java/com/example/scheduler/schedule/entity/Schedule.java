package com.example.scheduler.schedule.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity // DB 테이블로 인식
@Getter
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class) // 작성일, 수정일 자동 기록
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 기본키 컬럼의 값을 어떻게 자동 생성할지 지정하는 역할
    private Long id;

    // 작성자
    @Column(nullable = false)
    private String username;

    // 일정 제목
    @Column(nullable = false)
    private String title;

    // 일정 내용
    @Column(nullable = false) // 내용
    private String contents;

    // 생성일, 생성 시점 자동 기록
    @CreatedDate
    private LocalDateTime createdAt;

    // 수정일, 수정 시점 자동 기록
    @LastModifiedDate
    private LocalDateTime modifiedAt;

    // 생성자: DTO → Entity 변환 시 사용
    public Schedule(String username, String title, String contents) {
        this.username = username;
        this.title = title;
        this.contents = contents;
    }
}
