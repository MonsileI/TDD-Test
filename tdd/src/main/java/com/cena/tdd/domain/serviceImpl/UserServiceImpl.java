package com.cena.tdd.domain.serviceImpl;

import com.cena.tdd.domain.entity.User;
import com.cena.tdd.domain.repository.UserRepository;
import com.cena.tdd.domain.service.UserService;
import com.cena.tdd.domain.dto.UserReq;
import com.cena.tdd.domain.dto.UserRes;
import com.cena.tdd.global.exception.RegistrationFailedException;
import jakarta.transaction.Transactional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService {


    private static final Logger log = LogManager.getLogger(UserServiceImpl.class);
    private final UserRepository userRepository;


    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    @Override
    public UserRes regist(UserReq userReq) throws Exception {
        try {
            User user = userReq.toEntity();
            userRepository.save(user);
            UserRes userRes = UserRes.builder()
                            .userId(user.getUserId())
                            .userNickName(user.getUserNickname())
                            .build();
            return userRes;
        } catch(Exception e){
            log.error("회원 등록 중 에러 발생: {}", e.getMessage());
            throw new RegistrationFailedException("회원가입에 실패했습니다. 이유: " + e.getMessage());
        }
    }

    @Transactional
    @Override
    public UserRes infoUser(Long userIndex) throws Exception {
        User user = userRepository.findByUserIndex(userIndex);
        if(user==null) {
            String str = "회원 없음";
            log.error(str);
            return null;
        }
        UserRes userRes = UserRes.builder()
                .userId(user.getUserId())
                .userNickName(user.getUserNickname())
                .build();
        return userRes;
    }
}
