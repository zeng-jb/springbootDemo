package com.zeng;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;


@EnableAsync    //kaiqi异步
@EnableScheduling   //开启定时任务注解支持
@SpringBootApplication
public class Springboot09RenwuApplication {

    public static void main(String[] args) {
        SpringApplication.run(Springboot09RenwuApplication.class, args);
    }

}
