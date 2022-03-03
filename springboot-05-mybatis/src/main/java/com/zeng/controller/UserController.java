package com.zeng.controller;

import com.zeng.mapper.UserMapper;
import com.zeng.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/userlist")
    public List<User> queryUserList(){
        List<User> userList = userMapper.queryUserList();
        for (User user : userList) {
            System.out.println(user);
        }

        return userList;
    }

    //新增一个用户
    @GetMapping("/add")
    public String addUser(){
        //插入语句，注意时间问题
        userMapper.addUser(new User(3,"zeng","1111"));
        //查询
        return "addOk";
    }

    //修改用户信息
    @GetMapping("/update")
    public String updateUser(){
        userMapper.updateUser(new User(3,"xiaojiea","1111"));
        //查询
        return "updateOk";
    }

    //删除用户
    @GetMapping("/delete")
    public String delUser(){
        //插入语句
        userMapper.delUser(3);
        //查询
        return "deleteOk";
    }
}
