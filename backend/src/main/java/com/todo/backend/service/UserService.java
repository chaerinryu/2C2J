package com.todo.backend.service;

import com.todo.backend.controller.request.UserRequestdto;
import com.todo.backend.entity.UserEntity;
import com.todo.backend.repository.UserRepository;
import com.todo.backend.token.TokenVerifier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TokenVerifier tokenVerifier;

    // 사용자 정보 저장
    public UserEntity saveUser(UserRequestdto userRequestDto) {
        // UserRequestDto에서 사용자 정보를 추출합니다.
        String accessToken = userRequestDto.getAccessToken();
        String userId = userRequestDto.getUserId();
        String email = userRequestDto.getEmail();

        // User 엔티티를 생성하여 사용자 정보를 저장합니다.
        UserEntity userEntity = new UserEntity();
        userEntity.setAccessToken(accessToken);
        userEntity.setUserId(userId);
        userEntity.setEmail(email);

        // UserRepository를 사용하여 사용자 정보를 서버 DB에 저장합니다.
        return userRepository.save(userEntity);
    }
}
