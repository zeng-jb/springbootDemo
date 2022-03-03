package com.zeng.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.lang.reflect.Array;
import java.util.Arrays;

//在templates目录下的所有页面，只能通过controller访问支持来跳转！相当于springmvc中的web-inf目录
//需要模板引擎支持thymeleaf
@Controller
public class IndexController {

    @GetMapping("/test")
    public String index(Model model){

        model.addAttribute("msg","<h1>sssss</h1>");
        model.addAttribute("users", Arrays.asList("zeng","jiabin"));
        return "test";
    }
}
