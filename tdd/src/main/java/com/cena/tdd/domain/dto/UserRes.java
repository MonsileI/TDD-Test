package com.cena.tdd.domain.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class UserRes {


    //@Schema = open api나 swagger에서 사용하려고 쓰는 어노테이션
    private String userId;

    private String userNickName;


    @Builder
    public UserRes(String userId, String userNickName){
        this.userId = userId;
        this.userNickName = userNickName;

    }
}