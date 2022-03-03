package com.zeng.service;


import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class ScheduledService {

    //在特定的时间的执行这个方法，，，，timer
    //  cron 表达式    秒   分  时   日  月   周几
    @Scheduled(cron = "0/2 * * * * 0-7")
    public void Hello(){
        System.out.println("你被执行了、。。。。");

    }
}
