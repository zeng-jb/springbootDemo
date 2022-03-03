package com.zeng.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class helloController {

    @RequestMapping("/hello")
    public String  hello(){
        return "你好，师姐";
    }
}
