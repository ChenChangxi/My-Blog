package com.ccx.framework.shiro.realm;

import com.ccx.common.entity.PermissionEntity;
import com.ccx.common.entity.RoleEntity;
import com.ccx.common.entity.UserEntity;
import com.ccx.system.service.shiroservice.LoginService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @program: com.ccx.framework.shiro.realm
 * @description: 自定义的Realm
 * @authhor: ChenChangxi
 * @create: 2019-12-04 22:47
 **/

public class UserRealm extends AuthorizingRealm {


    @Autowired
    private LoginService loginService;

    /**
     *@Description:用户的角色和权限验证，它是用来验证你是否有登陆的权限
     *@Param:PrincipalCollection是
     *@return:AuthorizationInfo我们所配置的权限信息
     *@Author: ChenChangxi
     *@date: 2019-12-04
     */

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //获取用户名
        String username = (String) principalCollection.getPrimaryPrincipal();
        //查询用户信息
        UserEntity user = loginService.getUserByUserName(username);
        //为用户添加权限
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        for(RoleEntity role:loginService.getRoleByUserName(username)) {
            //添加角色
            authorizationInfo.addRole(role.getRoleName());
            for(PermissionEntity permission:loginService.getPermissionByUserName(username)) {
                //添加权限
                authorizationInfo.addStringPermission(permission.getPermissionName());
            }
        }
        return authorizationInfo;
    }

    /**
    *@Description:用户登陆信息验证，它来验证你的帐号密码是否有效的
    *@Param:AuthenticationToken
    *@return:AuthenticationInfo
    *@Author: ChenChangxi
    *@date: 2019-12-04
    */
    
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //没有输入账号密码
        if(authenticationToken.getPrincipal()==null) {
            return null;
        }
        //获取用户名
        String username=authenticationToken.getPrincipal().toString();
        //获取用户信息
        UserEntity user = loginService.getUserByUserName(username);
        //是否查到用户
        if(user == null) {
            return null;
        } else {
            //添加账号密码认证信息，交给matcher进行验证
            SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(username,user.getPassWord(),getName());
            return authenticationInfo;
        }
    }
}
