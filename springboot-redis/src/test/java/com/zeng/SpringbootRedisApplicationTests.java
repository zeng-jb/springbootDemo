package com.zeng;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zeng.pojo.User;
import com.zeng.service.MyPublisher;
import com.zeng.utils.RedisUtils;

import io.lettuce.core.RedisException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.data.redis.core.DefaultTypedTuple;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;

import java.util.*;

@SpringBootTest
class SpringbootRedisApplicationTests {

    @Autowired
    @Qualifier("redisTemplate")
    private RedisTemplate redisTemplate;
    @Autowired
    private RedisUtils redisUtil;
    @Test
    void contextLoads() throws JsonProcessingException {
        //开发一般使用json传递对象
        User user = new User("曾嘉彬",3);
        String valueAsString = new ObjectMapper().writeValueAsString(user);
        redisTemplate.opsForValue().set("user",valueAsString);

        System.out.println(redisTemplate.opsForValue().get("user"));
    }

    @Test
    public void test(){
        redisUtil.set("name","xxx");
        System.out.println(redisUtil.get("name"));
    }


    //============================redis-key=================================//
    @Test
    public void testkey(){
        //设置key过期时间
        redisUtil.expire("user",20);
        //获取key过期时间
        System.out.println(redisUtil.getExpire("user"));
        //判断key是否存在
        System.out.println(redisUtil.hasKey("user"));
        //删除key
        redisUtil.del("name","user");   //可删除多个
        //判断key的类型
        redisTemplate.type("user");
        //获取所有key
        Set keys = redisTemplate.keys("*");
        for (Object key : keys) {
            System.out.println(key);
        }
    }

    @Test
    public void clearAll(){
        //删除当前库所有键值对
        Set keys = redisTemplate.keys("*");
        redisTemplate.delete(keys);
    }

    //===========================redis-string=============================//
    @Test
    public void testString(){
        //set
        redisUtil.set("s1","zeng");
        //get
        System.out.println(redisUtil.get("s1"));
        //set+time
        redisUtil.set("s2","jia",15);
        //gettime
        System.out.println(redisUtil.get("s2")+" -> "+redisUtil.getExpire("s2"));
        redisUtil.set("age",20);
        //incr递增
        System.out.println(redisUtil.incr("age", 10));
        //decr递减
        System.out.println(redisUtil.decr("age", 5));

    }

    //==========================redis-list==============================//
    @Test
    public void testList(){
        //lSet为右插
        //从右插入rpush
        redisUtil.lSet("list1","v1");
        //time
        redisUtil.lSet("list1","v2",60);
        //多插
        redisUtil.lSet("list1",Arrays.asList("v3","v4","v5"));
        //获取缓存list长度
        redisUtil.lGetListSize("list1");
        //内容
        System.out.println(redisUtil.lGet("list1", 0, -1));
        //通过索引 获取list中的值
        System.out.println(redisUtil.lGetIndex("list1", 2));
        //根据索引修改list中的某条数据
        redisUtil.lUpdateIndex("list1",2,"v333");
        System.out.println(redisUtil.lGetIndex("list1", 3));
        //删除多个
        redisUtil.lSet("list1",Arrays.asList("v6","v6","v6"));
        System.out.println(redisUtil.lGet("list1", 0, -1));
        redisUtil.lRemove("list1",2,"v6");
        System.out.println(redisUtil.lGet("list1", 0, -1));
    }

    //==============================redis-set==============================//
    @Test
    public void testSet(){
        //sadd
        redisUtil.sSet("set1","v1");
        System.out.println(redisUtil.sGet("set1"));
        //sadd+time
        redisUtil.sSetAndTime("set2",60,"v2","v3");
        System.out.println(redisUtil.sGet("set2"));
        //多个内容
        redisUtil.sSet("set3","v1","v2","v3","v3","v4","v5");
        System.out.println(redisUtil.sGet("set3"));
        //查询值
        System.out.println("set1:v1" +  "    "+ redisUtil.sHasKey("set1", "v1"));
        System.out.println("set1:v2" +  "    "+ redisUtil.sHasKey("set1", "v2"));
        //长度
        System.out.println("set3:long"+"   "+redisUtil.sGetSetSize("set3"));
        //删除
        redisUtil.setRemove("set3","v1","v2");
        System.out.println(redisUtil.sGet("set3"));
    }
    //=============================redis-hash================================//
    @Test
    public void testMap(){
        //建立一张表
        redisUtil.hset("map1","name","zengjiabin");
        redisUtil.hset("map1","age","20",60);
        System.out.println(redisUtil.hmget("map1"));
        //多个
        Map<String,Object> map = new HashMap<String,Object>(){
            {
                put("name","zengjiabin");
                put("age",20);
                put("sex",1);
            }
        };
        redisUtil.hmset("map2",map);
        System.out.println(redisUtil.hmget("map2"));

        redisUtil.hdel("map2","sex");
        System.out.println(redisUtil.hget("map2", "sex"));
        System.out.println(redisUtil.hmget("map2"));

        redisUtil.hincr("map2","age",20);
        System.out.println(redisUtil.hget("map2", "age"));
        redisUtil.hdecr("map2","age",10);
        System.out.println(redisUtil.hget("map2", "age"));
    }
    //=============================redis-zset===========================//
    @Test
    public void testZset(){
        redisTemplate.opsForZSet().add("zset1","v1",1);


        ZSetOperations.TypedTuple<String> objectTypedTuple1 = new DefaultTypedTuple<>("eee",9.6);
        ZSetOperations.TypedTuple<String> objectTypedTuple2 = new DefaultTypedTuple<>("fff",1.5);
        ZSetOperations.TypedTuple<String> objectTypedTuple3 = new DefaultTypedTuple<>("ggg",7.4);
        Set<ZSetOperations.TypedTuple<String>> typles = new HashSet<>();
        typles.add(objectTypedTuple1);
        typles.add(objectTypedTuple2);
        typles.add(objectTypedTuple3);
        redisTemplate.opsForZSet().add("zset1", typles);


        System.out.println(redisTemplate.opsForZSet().range("zset1", 0, -1));
    }
    //=============================redis-事务==================================//
    //Redis的单条命令是保证原子性的，但是redis事务不能保证原子性
    @Test
    public void testTransaction(){

        redisTemplate.setEnableTransactionSupport(true);//开启事务支持
        try{
            redisTemplate.multi();//开始事务

            //redisUtil.set("transaction","1");
            redisUtil.set("transaction",1);
            redisUtil.hset("transaction1","hash","test");
            System.out.println(redisUtil.get("transaction"));//因为事务还未提交，所以是null
            //System.out.println(redisUtil.incr("transaction", 10));//同理

            redisTemplate.exec();//提交事务
        }catch (Exception e){
            System.out.println("redis事务回滚了");
            redisTemplate.discard();//事务回滚
        }

    }
    //=================================redis-乐观锁========================//


    //==================================发布订阅===========================//
    @Autowired
    private MyPublisher myPublisher;

    @Test
    public void testPub(){
        myPublisher.sendMsg("hello,world...1");
        myPublisher.sendMsg("hello,world...2");
        myPublisher.sendMsg("hello,world...3");
        myPublisher.sendMsg("hello,world...4");
        myPublisher.sendMsg("hello,world...5");
    }

    //==================================redis-集群========================//
    //先去application.yml注释取消掉
    @Test
    public void testCluster(){
        for (int i = 0; i < 10000; i++) {
            redisUtil.set("key"+i,i);
        }
        Set keys = redisTemplate.keys("*");
        System.out.println(keys.size());
    }

    //=================================redis-主从复制。哨兵模式======================//
    @Test
    public void testSentinel(){
        for (int i = 1; i <= 10; i++) {
            redisUtil.set("str"+i,i);
        }
    }
}
