package com.zeng.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class JDBCController {

    @Autowired
    JdbcTemplate jdbcTemplate;

    //查询数据库所有信息
    //没有实体类，数据库中东西可以用Map获取
    @GetMapping("/userlist")
    public List<Map<String,Object>> userList(){

        String sql = "select * from user";
        List<Map<String, Object>> mapList = jdbcTemplate.queryForList(sql);
        return mapList;
    }

    @GetMapping("/adduser")
    public String addUser(){
        String sql = "insert into user(id,name,pwd) values (3,'小曾','123445')";
        jdbcTemplate.update(sql);
        return "update-ok";
    }

    @GetMapping("/updateuser/{id}")
    public String updateUser(@PathVariable("id") int id){
        String sql = "update user set name=?,pwd=? where id="+id;

        //封装
        Object[] objects = new Object[2];
        objects[0] = "小明2";
        objects[1] = "zzzzzz";
        jdbcTemplate.update(sql,objects);

        return "updateUser-ok";
    }

    @GetMapping("/deleteuser/{id}")
    public String deleteUser(@PathVariable("id") int id){
        String sql = "delete from user where id=?";
        jdbcTemplate.update(sql,id);
        return "delete-ok";
    }
}
