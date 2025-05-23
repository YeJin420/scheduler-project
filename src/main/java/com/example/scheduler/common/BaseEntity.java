package com.example.scheduler.common;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import java.time.LocalDateTime;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 * 모든 엔티티에서 공통으로 사용하는 생성일자, 수정일자 필드를 관리하는 추상 클래스입니다.
 */
@Getter
@MappedSuperclass // JPA 엔티티 클래스들이 이 클래스를 상속받아 필드를 공유할 수 있도록 설정
@EntityListeners(AuditingEntityListener.class) // JPA Auditing 기능을 활성화하여 자동으로 날짜를 관리
public abstract class BaseEntity {

    @CreatedDate // 엔티티가 생성되어 저장될 때 시간 자동 저장
    @Column(updatable = false) // 생성일자는 수정되지 않도록 설정
    private LocalDateTime createdAt;

    @LastModifiedDate // 엔티티가 수정될 때 시간 자동 저장
    private LocalDateTime modifiedAt;
}
