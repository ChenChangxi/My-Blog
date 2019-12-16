package com.ccx.junit.shiro;

import com.ccx.common.entity.PermissionEntity;
import com.ccx.common.entity.RoleEntity;
import com.ccx.common.entity.UserEntity;
import com.ccx.system.mapper.shiromapper.PermissionMapper;
import com.ccx.system.mapper.shiromapper.RoleMapper;
import com.ccx.system.mapper.shiromapper.UserMapper;
import com.ccx.system.service.shiroservice.LoginService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashSet;
import java.util.Set;

/**
 * @program: com.ccx.junit.shiro
 * @description: 这是为了测试Shiro是否被整合成功而创建的测试类，
 * 在这里，我主要是验证权限的mapper有没有有写成功，往权限验证数据
 * 库中插入一些数据。
 * @authhor: ChenChangxi
 * @create: 2019-12-05 17:33
 **/

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class ShiroTest {

    private int permissions=10;

    private int users=5;

    private int roles=3;

    @Autowired
    private LoginService loginService;

    @Test
    public void insertUser() {
        UserEntity user=new UserEntity();
        Set<String> roleNames=new HashSet<String>();
        user.setUserName("陈昌熙");
        user.setPassWord("QTSS.a000");
        user.setSalt("没有加盐");
        roleNames.add("管理员");
        roleNames.add("老板");
        user.setRoles(roleNames);
        loginService.setUser(user);
    }

    @Test
    public void insertRole() {   //插入角色
        RoleEntity role=new RoleEntity();
        Set<String> permissionNames=new HashSet<String>();
        permissionNames.add("第2权限");
        permissionNames.add("第4权限");
        permissionNames.add("第6权限");
        role.setRoleName("管理员");
        role.setPermissions(permissionNames);
        loginService.setRole(role);
        permissionNames.clear();
        permissionNames.add("第1权限");
        permissionNames.add("第2权限");
        permissionNames.add("第9权限");
        role.setRoleName("老板");
        role.setPermissions(permissionNames);
        loginService.setRole(role);
    }

    @Test
    public void insertPermission() {   //插入权限
        PermissionEntity permission=new PermissionEntity();
        for(int i=0;i<permissions;++i) {
            permission.setPermissionName("第"+i+"权限");
            loginService.setPermission(permission);
        }
    }
}
