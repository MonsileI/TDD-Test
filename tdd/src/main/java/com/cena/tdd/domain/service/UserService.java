package com.cena.tdd.domain.service;


import com.cena.tdd.domain.dto.UserReq;
import com.cena.tdd.domain.dto.UserRes;

public interface UserService {

    UserRes regist(UserReq userReq) throws Exception;

    UserRes infoUser(Long userIndex) throws Exception;

}
