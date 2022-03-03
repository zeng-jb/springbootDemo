package com.zeng.mapper;

import com.zeng.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

@Repository
@Mapper
public interface UserMapper {

    @Select("select * from user where name = #{name}")
    public User queryUserByName( String name);
}
