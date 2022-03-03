package com.zeng;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Springboot02ConfigApplication {

    public static void main(String[] args) {
        SpringApplication.run(Springboot02ConfigApplication.class, args);
    }

}

/*

优先级1：项目路径下的config文件夹配置文件
优先级2：项目路径下配置文件
优先级3：资源路径下的config文件夹配置文件
优先级4：资源路径下配置文件
 */
