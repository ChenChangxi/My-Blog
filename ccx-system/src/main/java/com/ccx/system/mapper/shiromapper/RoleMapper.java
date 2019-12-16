package com.ccx.system.mapper.shiromapper;

import com.ccx.common.entity.RoleEntity;
import org.apache.ibatis.annotations.*;

import java.util.Set;

/**
 * @program: com.ccx.system.mapper.shiromapper
 * @description: 这是对角色及其关系进行的操作
 * @authhor: ChenChangxi
 * @create: 2019-12-09 09:24
 **/

@Mapper
public interface RoleMapper {

    @Insert("INSERT INTO ROLE(ROLE_NAME,DESCRIPTIONS) VALUES(#{rolename},#{description})")
    public void insertRole(@Param("rolename") String roleName,
                           @Param("description") String description);

    @Insert("INSERT INTO USER_ROLE VALUES(" +
            "(SELECT USER_ID FROM USER WHERE USER_NAME=#{username})," +
            "(SELECT ROLE_ID FROM ROLE WHERE ROLE_NAME=#{rolename}))")
    public void insertUserRole(@Param("username") String userName,
                               @Param("rolename") String roleName);

    @Select("SELECT * FROM ROLE WHERE ROLE_NAME=#{rolename}")
    @Results(value = {
            @Result(column = "ROLE_ID",property = "roleId"),
            @Result(column = "ROLE_NAME",property = "roleName"),
            @Result(column = "DESCRIPTIONS",property = "description")
    })
    public RoleEntity selectByRoleName(@Param("rolename") String roleName);

    @Select("SELECT ROLE_NAME FROM ROLE,USER_ROLE,USER WHERE USER_NAME={username}")
    public Set<String> selectByUserName(@Param("username") String username);
}
