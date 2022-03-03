package com.zeng.mapper;

import com.zeng.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.stereotype.Repository;

import java.util.List;

//@MapperScan   加在启动类上面扫描mapper。
@Mapper         //表示这个接口是mybatis接管的mapper类
@Repository     //dao类被spring接管     也可以componentscan扫描spring注解
public interface UserMapper {

    List<User> queryUserList();

    User queryUserById(int id);

    int addUser(User user);

    int updateUser(User user);

    int delUser(int id);
}



