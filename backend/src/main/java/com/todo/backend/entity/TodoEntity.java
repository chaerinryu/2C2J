package com.todo.backend.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "todo")
@NoArgsConstructor
public class TodoEntity {
    @Id // 기본키로 설정
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 자동으로 생성
    private int id;

    @Column(nullable = false, length = 45) // 널 값 허용 여부, 문자열 개수(디폴트 255)
    private String title;

    @Column(nullable = false)
    private String content;

    private String memo; // @Column 어노테이션은 선택적, 각 컬럼의 널 값이나 기본값 설정 등에 사용

    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime startdate;

    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime enddate;

    @Enumerated(EnumType.STRING) // 열거형 상수의 문자열 값을 DB에 저장하고 읽음
    private DoType dotype = DoType.DO;

    @ManyToOne(fetch = FetchType.LAZY) // 다대일 관계, LAZY(지연 로딩)/EAGER(즉시 로딩)
    @JoinColumn(name = "user_id", nullable = false) // 외래키 지정
    private UserEntity userId;

    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime lastData;
}
