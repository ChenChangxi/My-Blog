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

    //密码用哈希散列算法加密
    public void insertUser(UserEntity user) {
        String sha384Hex=new Sha384Hash(user.getPassWord()).toBase64();
        user.setPassWord(sha384Hex);
        userMapper.insertUser(user);
    }

    public UserEntity getUserByUserName(String userName) {
        return userMapper.selectByName(userName);
    }

    public Set<RoleEntity> getRoleByUserName(String username) {
        return roleMapper.selectByName(username);
    }

    public Set<PermissionEntity> getPermissionByUserName(String userName) {

        Set<PermissionEntity> permissionSet = new HashSet<PermissionEntity>();
        Set<RoleEntity> roles = roleMapper.selectByName(userName);
        for(RoleEntity role:roles) {
            Set<PermissionEntity> permissions = permissionMapper.selectByRoleName(role.getRoleName());
            for(PermissionEntity permission:permissions) {
                permissionSet.add(permission);
            }
        }
        return permissionSet;
    }
}
