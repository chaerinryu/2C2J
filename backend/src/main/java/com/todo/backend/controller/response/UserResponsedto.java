package com.todo.backend.controller.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserResponsedto {
    // 저장된 값 중 어떤 값을 받아올지 정의
    private String email;
}
