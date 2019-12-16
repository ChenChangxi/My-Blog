package com.ccx.system.service.shiroservice.serviceimp;

import com.ccx.common.entity.PermissionEntity;
import com.ccx.common.entity.RoleEntity;
import com.ccx.common.entity.UserEntity;
import com.ccx.system.mapper.shiromapper.PermissionMapper;
import com.ccx.system.mapper.shiromapper.RoleMapper;
import com.ccx.system.mapper.shiromapper.UserMapper;
import com.ccx.system.service.shiroservice.LoginService;
import org.apache.shiro.crypto.hash.Sha384Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.management.relation.Role;
import java.util.HashSet;
import java.util.Set;

/**
 * @program: com.ccx.system.service.shiroservice.serviceimp
 * @description: 这是ShiroService接口的实现类
 * @authhor: ChenChangxi
 * @create: 2019-12-04 23:03
 **/

@Service
public class LoginServiceImp implements LoginService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private PermissionMapper permissionMapper;

    //密码用哈希散列算法加密，插入一个用户
    public void setUser(UserEntity user) {
        String userName=user.getUserName();
        String sha384Hex=new Sha384Hash(user.getPassWord()).toBase64();
        String salt=user.getSalt();
        String description=user.getDescription();
        userMapper.insertUser(userName,sha384Hex,salt,description);
        for(String roleName:user.getRoles()) {
            roleMapper.insertUserRole(userName,roleName);
        }
    }

    public void setRole(RoleEntity role) {
        String roleName=role.getRoleName();
        String description=role.getDescription();
        roleMapper.insertRole(roleName,description);
        for(String permissionName:role.getPermissions()) {
            permissionMapper.insertRolePermission(roleName,permissionName);
        }
    }

    public void setPermission(PermissionEntity permission) {
        String permissionName=permission.getPermissionName();
        String description=permission.getDescription();
        permissionMapper.insertPermission(permissionName,description);
    }

    public UserEntity getUserByUserName(String userName) {
        UserEntity user=userMapper.selectByUserName(userName);
        Set<String> roleNames=roleMapper.selectByUserName(userName);
        user.setRoles(roleNames);
        return user;
    }

    public RoleEntity getRoleByRoleName(String roleName) {
        RoleEntity role=roleMapper.selectByRoleName(roleName);
        Set<String> permissions=permissionMapper.selectByRoleName(roleName);
        role.setPermissions(permissions);
        return role;
    }

    public PermissionEntity getPermissionByPermissionName(String permissionName) {
        return permissionMapper.selectByPermissionName(permissionName);
    }

    public Set<String> getPermissionsByUserName(String userName) {
        Set<String> permissionNames=new HashSet<String>();
        Set<String> roleNames=roleMapper.selectByUserName(userName);
        for(String roleName:roleNames) {
            Set<String> permissionName=permissionMapper.selectByRoleName(roleName);
            permissionNames.addAll(permissionNames);
        }
        return permissionNames;
    }
}
