package com.cena.tdd.test;

import org.junit.jupiter.api.*;

public class TestLifeCycle {

    @BeforeAll                         //테스트 어노테이션 붙은 애들 모두 시작 전 작동
    static void beforeAll(){
        System.out.println("## BeforeAll Annotation 호출 ##");
        System.out.println();
    }

    @AfterAll                          //테스트 어노테이션 붙은 애들 모두 끝난 후 작동
    static void afterAll(){
        System.out.println("## AfterAll Annotation 호출 ##");
        System.out.println();
    }
    @BeforeEach
    void beforeEach(){                 //각각 테스트 어노테이션 붙은 애들 시작 전 작동
        System.out.println("## beforeEach Annotation 호출 ##");
        System.out.println();
    }
    @AfterEach
    void afterEach(){                  //각각 테스트 어노테이션 붙은 애들 끝난 후 작동
        System.out.println("## afterEach Annotation 호출 ##");
        System.out.println();
    }


    @Test
    void test1(){
        System.out.println("## test 1 시작 ##");
        System.out.println();
    }

    @Test
    @DisplayName("Test Case 2~!")     //콘솔에 노출되는 테스트 명
    void test2(){
        System.out.println("## test 2 시작 ##");
        System.out.println();
    }

    @Test
    @Disabled                         //테스트 실행 안하게 하는 어노테이션
    void test3(){
        System.out.println("## test 3 시작 ##");
        System.out.println();
    }
}
