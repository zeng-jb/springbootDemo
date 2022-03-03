package com.zeng.controller;


import com.zeng.pojo.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(tags = "hello控制类")
public class HelloController {

    @ApiOperation("hello方法")
    @RequestMapping(value = "/hello")
    public String hello(){
        return "hello";
    }

    //只要我们接口返回存在实体类，他就会被扫描到swgger中
    @PostMapping(value = "/user")
    public User user(){
        return new User();
    }
}
