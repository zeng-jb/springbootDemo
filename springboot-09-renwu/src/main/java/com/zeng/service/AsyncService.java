package com.zeng.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class AsyncService {
    //异步任务，多线程实现
    //异步方法
    @Async
    public void hello(){

        try{
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("数据正在处理。。。。");
    }
}
