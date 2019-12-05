package com.ccx.common.entity;

import java.util.Set;

/**
 * @program: com.ccx.common.entity
 * @description: 这是一个用户的实体类
 * @authhor: ChenChangxi
 * @create: 2019-12-05 00:29
 **/

public class UserEntity extends BaseEntity{

    private String userId;

    private String userName;

    private String passWord;

    private Set<RoleEntity> roles;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public Set<RoleEntity> getRoles() {
        return roles;
    }

    public void setRoles(Set<RoleEntity> roles) {
        this.roles = roles;
    }
}
