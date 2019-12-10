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

    public UserEntity getUserByUserName(String username);

    public void insertUser(UserEntity user);

    public Set<RoleEntity> getRoleByUserName(String username);

    public Set<PermissionEntity> getPermissionByUserName(String userame);
}
