package com.zeng.service;

import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Service;

// 消费者
@Service
public class MyListener implements MessageListener {

    @Override
    public void onMessage(Message message, byte[] pattern) {
        System.out.println(new String(message.getBody()));
    }
}
