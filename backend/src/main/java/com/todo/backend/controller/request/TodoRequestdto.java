package com.todo.backend.controller.request;

import com.todo.backend.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class TodoRequestdto {
    // 입력 데이터 정의
    private int id;
    private String title;
    private String content;
    private String memo;
    private String dotype;
    private LocalDateTime lastData;
    private UserEntity userId;
}
