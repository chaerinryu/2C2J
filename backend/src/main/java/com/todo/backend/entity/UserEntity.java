package com.todo.backend.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "user")
@NoArgsConstructor
public class UserEntity {
    // 테이블 설정
    @Id
    @Column(nullable = false)
    private String userId;
    @Column(nullable = false)
    private String accessToken; // 이건 저장할 필요 없을듯? 일단 해놓음
    @Column(nullable = false)
    private String email;

    /*@Column(nullable = false)
    private String name;*/

    // 기본적으로 디폴트 값이 있으면 nullable = false로 설정된다고 합니다
    /*private int followers = 0; // 기본값 0
    private int followings = 0;*/
}
