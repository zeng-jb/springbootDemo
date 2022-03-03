package com.zeng.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component      //注册bean到容器中
public class Dog {
    @Value("小环")
    private String name;
    @Value("3")
    private Integer age;
}
