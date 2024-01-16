package com.cena.tdd.domain.controller;

import com.cena.tdd.domain.dto.UserReq;
import com.cena.tdd.domain.dto.UserRes;
import com.cena.tdd.domain.service.UserService;
import com.cena.tdd.domain.serviceImpl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {


    private static final Logger log = LogManager.getLogger(UserServiceImpl.class);

    @Autowired
    private final UserService userService;

    @Autowired
    public UserController(UserService userService){ this.userService = userService; }


    @PostMapping("/regist")
    public ResponseEntity<?> regist(@RequestBody UserReq userReq, HttpServletRequest request) throws Exception{

        try {
            UserRes userRes = userService.regist(userReq);
            return new ResponseEntity<UserRes>(userRes, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/info/{userIndex}")
    public ResponseEntity<?> info(@PathVariable Long userIndex, HttpServletRequest request) throws Exception{

        try {
            UserRes userRes = userService.infoUser(userIndex);
            if(userRes==null) return new ResponseEntity<String>("회원없음",HttpStatus.OK);
            return new ResponseEntity<UserRes>(userRes, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}