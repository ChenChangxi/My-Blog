package com.ccx.system.mapper.shiromapper;

import com.ccx.common.entity.UserEntity;
import org.apache.ibatis.annotations.*;

import java.util.Set;

/**
 * @program: com.ccx.system.mapper.shiromapper
 * @description: 这是对用户及其相应关系的操作
 * @authhor: ChenChangxi
 * @create: 2019-12-09 09:23
 **/

@Mapper
public interface UserMapper {

    @Insert("INSERT INTO USER VALUS(" +
            "#{user.userName},#{user.password},#{user.salt},#{user.description})")
    public void insertUser(@Param("user") UserEntity user);

    @Select("SELECT * FROM USER WHERE USER_NAME=#{username}")
    @Results(value = {
            @Result(column = "USER_ID",property = "userId"),
            @Result(column = "USER_NAME",property = "userName"),
            @Result(column = "PASSWORD",property = "password"),
            @Result(column = "DESCRIPTION",property = "description"),
            @Result(column = "SALT",property = "salt")
    })
    public UserEntity selectByName(String username);

}
