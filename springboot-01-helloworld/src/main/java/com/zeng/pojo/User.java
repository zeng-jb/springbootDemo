package com.zeng.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component          //注册bean
@PropertySource(value = "classpath:user.properties")        //加载指定配置文件
@Validated          //数据校验
public class User {
    @Value("${User.name}")
    private String name;
    @Value("${User.age}")
    private int age;
}
