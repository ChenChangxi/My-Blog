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

    @Insert("INSERT INTO PERMISSION(PERMISSION_NAME,DESCRIPTIONS) VALUES (#{permissionname},#{description})")
    public void insertPermission(@Param("permissionname") String permissionName,
                                 @Param("description") String description);

    @Insert("INSERT INTO ROLE_PERMISSION VALUES(" +
            "(SELECT ROLE_ID FROM ROLE WHERE ROLE_NAME=#{rolename})," +
            "(SELECT PERMISSION_ID FROM PERMISSION WHERE PERMISSION_NAME=#{permissionname}))")
    public void insertRolePermission(@Param("rolename") String roleName,
                                     @Param("permissionname") String permissionName);

    @Select("SELECT * FROM PERMISSION WHERE PERMISSION_NAME=#{permissionname}")
    @Results(value = {
            @Result(column = "PERMISSION_ID", property = "permissionId"),
            @Result(column = "PERMISSION_NAME", property = "permissionName"),
            @Result(column = "DESCRIPTIONS",property = "description")
    })
    public PermissionEntity selectByPermissionName(@Param("permissionname") String roleName);

    @Select("SELECT PERMISSION_NAME FROM ROLE,ROLE_PERMISSION,PERMISSION WHERE ROLE_NAME={rolename}")
    public Set<String> selectByRoleName(@Param("rolename") String roleName);
}
