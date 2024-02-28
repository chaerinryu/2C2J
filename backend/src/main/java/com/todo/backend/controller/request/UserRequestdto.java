package com.todo.backend.controller.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserRequestdto {
    // 값을 저장해서 JSON 형식으로 프론트에 보내는 용도
    private String userId;
    private String accessToken;
    private String email;
}
