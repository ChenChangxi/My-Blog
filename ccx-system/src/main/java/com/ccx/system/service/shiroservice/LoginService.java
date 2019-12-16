package com.ccx.system.service.shiroservice;

import com.ccx.common.entity.PermissionEntity;
import com.ccx.common.entity.RoleEntity;
import com.ccx.common.entity.UserEntity;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * @program: com.ccx.system.service.shiroservice
 * @description: 这是验证登陆信息的service接口
 * @authhor: ChenChangxi
 * @create: 2019-12-04 23:01
 **/

public interface LoginService {

    /**
    *@Description: 插入一个用户
    *@Param: UserEntity，当中有用户所拥有的角色集合
    *@Author: ChenChangxi
    *@date: 2019-12-16
    */
    public void setUser(UserEntity user);

    /**
    *@Description: 插入一个角色
    *@Param: RoleEntity，当中有角色所拥有的权限集合
    *@Author: ChenChangxi
    *@date: 2019-12-16
    */
    public void setRole(RoleEntity role);

    /**
    *@Description: 插入一个权限
    *@Author: ChenChangxi
    *@date: 2019-12-16
    */
    public void setPermission(PermissionEntity permission);

    /**
    *@Description: 根据用户名得到某一个用户。
    *@Param: username用户名
    *@return: 用户的信息实体，包括用户所拥有的角色
    *@Author: ChenChangxi
    *@date: 2019-12-16
    */

    public UserEntity getUserByUserName(String userName);


    /**
    *@Description: 根据用户名得到某一用户所拥有的角色。
    *@Param: 用户名username
    *@return: 用户所拥有的角色信息集合，包括角色拥有的权限
    *@Author: ChenChangxi
    *@date: 2019-12-16
    */
    public RoleEntity getRoleByRoleName(String userName);

    /**
    *@Description: 通过某一权限的名称得到所有的权限信息。
    *@Param: 权限名称permissionName
    *@return: 权限实体
    *@Author: ChenChangxi
    *@date: 2019-12-16
    */
    public PermissionEntity getPermissionByPermissionName(String permissionName);

    /**
    *@Description: 根据用户名得到某一用户所拥有的权限
    *@Param: 用户名username
    *@return: 用户所拥有的权限集合
    *@Author: ChenChangxi
    *@date: 2019-12-16
    */
    public Set<String> getPermissionsByUserName(String userName);
}
