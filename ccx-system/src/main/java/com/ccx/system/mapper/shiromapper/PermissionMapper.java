package com.ccx.system.mapper.shiromapper;

import com.ccx.common.entity.PermissionEntity;
import org.apache.ibatis.annotations.*;

import java.util.Set;

/**
 * @program: com.ccx.system.mapper.shiromapper
 * @description: 这是对用户权限所进行的操作
 * @authhor: ChenChangxi
 * @create: 2019-12-10 10:52
 **/

@Mapper
public interface PermissionMapper {

    @Insert("INSERT INTO PERMISSION VALUES(" +
            "#{permission.permissionName},#{permission.description})")
    public void insertPermission(@Param("permission") PermissionEntity permission);

    @Select("SELECT PERMISSION_ID,PERMISSION_NAME,DESCRIPTION FROM PERMISSION" +
            "WHERE PERMISSION_ID IN (SELECT PERMISSION_ID FROM ROLE_PERMISSION" +
            "WHERE ROLE_ID IN (SELECT ROLE_ID FROM ROLE WHERE ROLE_NAME=#{roleName}))")
    @Results(value = {
            @Result(column = "PERMISSION_ID", property = "permissionId"),
            @Result(column = "PERMISSION_NAME", property = "permissionName"),
            @Result(column = "DESCRIPTION",property = "description")

    })
    public Set<PermissionEntity> selectByRoleName(String roleName);

}
