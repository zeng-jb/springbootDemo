package com.zeng.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.stereotype.Service;

// 生产者
@Service
public class MyPublisher {


    @Autowired
    @Qualifier("redisTemplate")
    private RedisTemplate redisTemplate;

    // 跟rabbitmq的 queue是一样的 订阅topic
    public static final String test_subscribe_topic = "test-topic";
    private ChannelTopic topic = new ChannelTopic(test_subscribe_topic);

    public void sendMsg(String msg) {
        redisTemplate.convertAndSend(topic.getTopic(), "收到消息了吗!:" + msg);
    }
}
