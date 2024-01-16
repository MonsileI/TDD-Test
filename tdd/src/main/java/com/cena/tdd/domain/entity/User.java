package com.cena.tdd.domain.entity;


import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
@Entity
@Table(name="user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_index",unique= true,nullable =false)
    private Long userIndex;

    @Column(length = 15, nullable = false)
    private String userId;

    @Column(length = 15, nullable = false)
    private String userPw;

    @Column(length = 15, nullable = false)
    private String userNickname;

    @Builder
    public User(Long userIndex, String userId, String userPw, String userNickname){
        this.userIndex = userIndex;
        this.userId = userId;
        this.userPw = userPw;
        this.userNickname = userNickname;
    }

}
