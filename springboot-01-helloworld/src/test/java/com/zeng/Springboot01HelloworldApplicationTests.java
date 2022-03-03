package com.zeng;

import com.zeng.pojo.Dog;
import com.zeng.pojo.Person;
import com.zeng.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Springboot01HelloworldApplicationTests {

    @Autowired          //将狗类自动注入进来
    Dog dog;

    @Autowired
    Person person;

    @Autowired
    User user;
    @Test
    void contextLoads() {
        System.out.println(person);
    }



}
