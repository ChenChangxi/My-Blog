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

    @Insert("INSERT INTO ROLE VALUES(#{role.roleName},#{role.description})")
    public void insertRole(@Param("role") RoleEntity role);

    @Select("SELECT ROLE_ID,ROlE_NAME,DESCRIPTION FROM ROLE WHERE ROLE_ID IN(" +
            "SELECT ROLE_ID FROM USER_ROLE WHERE USER_ID IN (SELECT USER_ID FROM" +
            "USER WHERE USER_NAME=#{username}))")
    @Results(value = {
            @Result(column = "ROLE_ID",property = "roleId"),
            @Result(column = "ROLE_NAME",property = "roleName"),
            @Result(column = "DESCRIPTION",property = "description")
    })
    public Set<RoleEntity> selectByName(String username);
}
