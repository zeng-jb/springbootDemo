package com.zeng.service;

import org.apache.dubbo.config.annotation.Service;
import org.springframework.stereotype.Component;

@Service    //可以被扫描到，在项目启动就自动注册到注册中心
@Component  //使用dubbo后劲量不要使用Service注解
public class TicketServiceImpl implements TicketService{
    @Override
    public String getTicket() {
        return "zengjiabinnihaoaaaa";
    }
}
