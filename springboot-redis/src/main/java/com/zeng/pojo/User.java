package com.zeng.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.stereotype.Component;

import java.io.Serializable;


@Component      //组件
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {     //给pojo类序列化import java.io.Serializable;
    private String name;
    private Integer age;


}
