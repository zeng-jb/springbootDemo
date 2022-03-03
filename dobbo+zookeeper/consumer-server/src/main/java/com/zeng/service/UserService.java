package com.zeng.service;

import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Service;


@Service    //放到容器中
public class UserService {
    //http    rpc   想拿到provider-server   要去注册中心拿到服务

    @Reference  //应用，pom坐标，可以定义路径相同的接口名
    TicketService tick;

    public void buyTickket(){
        String ticket = tick.getTicket();
        System.out.println("在注册中心拿到----》》"+ticket);
    }
}
