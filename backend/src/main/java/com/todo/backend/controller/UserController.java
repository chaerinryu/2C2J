package com.todo.backend.controller;

import com.todo.backend.controller.request.UserRequestdto;
import com.todo.backend.entity.UserEntity;
import com.todo.backend.service.UserService;
import com.todo.backend.token.TokenVerifier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {
    // api 설정하고 어떤 데이터를 보내고 받을지

    @Autowired
    private UserService userService;
    @Autowired
    private TokenVerifier tokenVerifier;

    // 사용자 정보 저장
    @PostMapping("/save")
    public UserEntity saveUser(@RequestBody UserRequestdto userRequestdto) {
        return userService.saveUser(userRequestdto);
    }
}