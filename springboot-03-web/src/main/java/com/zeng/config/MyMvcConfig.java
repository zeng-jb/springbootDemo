package com.zeng.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Locale;
//如果想diy一些定制化的功能，只要写这个组件，然后把它交给springboot，springboot会自动帮装配
//拓展springmvc
@Configuration
public class MyMvcConfig implements WebMvcConfigurer {

    //ViewResolver 实现了视图解析器接口的类，我们可以看做他微视图解析器


    //自定义了一个自己的视图解析器MyViewResolver
    public static class MyViewResolver implements ViewResolver{
        @Override
        public View resolveViewName(String viewName, Locale locale) throws Exception {
            return null;
        }
    }
    //视图跳转
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        //registry.addViewController("/zeng").setViewName("test");
    }
}
