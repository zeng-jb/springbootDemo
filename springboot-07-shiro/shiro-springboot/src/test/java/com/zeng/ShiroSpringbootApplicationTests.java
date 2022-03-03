package com.zeng;

import com.zeng.service.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ShiroSpringbootApplicationTests {

    @Test
    void contextLoads() {
    }

    @Autowired
    UserServiceImpl userService;
    @Test
    public void test1(){
        System.out.println(userService.queryUserByName("曾嘉彬"));
    }


}
