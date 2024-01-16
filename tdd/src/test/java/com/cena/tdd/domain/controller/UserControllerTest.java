package com.cena.tdd.domain.controller;


import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.cena.tdd.domain.dto.UserReq;
import com.cena.tdd.domain.dto.UserRes;
import com.cena.tdd.domain.service.UserService;
import com.google.gson.Gson;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest
//@AutoConfigureWebMvc //MockMvc를 Builder 없이 주입 받을 수 있게 해줌
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    //UserController에서 잡고 있는 Bean 객체에 대해 Mock 형태(가짜) 객체를 생성해 줌
    @MockBean
    UserService userService;

    @Test
    @DisplayName("regist : 유저 등록 테스트")
    void regist() throws Exception{

        given(userService.regist(new UserReq("testID","testPassword","testNickName"))).willReturn(
                new UserRes("testID","testNickName")
        );

        UserReq userReq = UserReq.builder()
                .userId("testID")
                .userPw("testPassword")
                .userNickName("testNickName")
                .build();


        Gson gson = new Gson();   //객체를 json 형태로 바꿔주는 갓gle 제공 디펜던시


        String content = gson.toJson(userReq);  //제이슨으로 바꿔줘
        //String json = new ObjectMapper().writeValueAsString(userReq)// 이렇게 대처도 가능

        System.out.println("JSON : "+content);

        mockMvc.perform(
                        post("/user/regist")
                                .content(content)
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userId").exists())
                .andExpect(jsonPath("$.userNickName").exists())
                .andDo(print());

        verify(userService).regist(new UserReq("testID","testPassword","testNickName"));

    }


    @Test
    @DisplayName("info : 유저 정보 조회 테스트")
    void info() throws Exception{

        //given : Mock 객체가 특정 상황에서 해야하는 행위를 정의하는 메소드 //willReturn = return 값이 넘어올거야~ 라고 예상하는 값
        given(userService.infoUser(1L)).willReturn(
           new UserRes("testID","testNickName")
        );

        Long userIndex = 1L;

        // andExpect : 기대하는 값이 나왔는지 체크하는 메소드

        mockMvc.perform(
                get("/user/info/" + userIndex))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userId").exists())        //json path의 depth가 깊어지면 .을 추가 탐색 가능 (ex -> $.userId.userNickName)
                .andExpect(jsonPath("$.userNickName").exists())
                .andDo(print());

        //verify : 해당 객체의 메소드가 실행되었는지 체크
        verify(userService).infoUser(1L);

    }
}
