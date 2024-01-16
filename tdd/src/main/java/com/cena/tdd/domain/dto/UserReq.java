package com.cena.tdd.domain.dto;

import com.cena.tdd.domain.entity.User;
import lombok.*;

import java.util.Objects;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class UserReq {


    private String userId;


    private String userPw;


    private String userNickName;


    public User toEntity() {
        return User.builder()
                .userId(userId)
                .userPw(userPw)
                .userNickname(userNickName)
                .build();
    }
}
