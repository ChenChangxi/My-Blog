package com.ccx.common.entity;

import java.math.BigInteger;
import java.util.Set;

/**
 * @program: com.ccx.common.entity
 * @description: 这是一个用户的实体类
 * @authhor: ChenChangxi
 * @create: 2019-12-05 00:29
 **/

public class UserEntity extends BaseEntity{

    private BigInteger userId;  //数据库中就是bigint

    private String userName;

    private String password;

    private String salt;

    private String description;

    private Set<String> roles;

    public BigInteger getUserId() {
        return userId;
    }

    public void setUserId(BigInteger userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public void setPassWord(String passWord) {
        this.password = passWord;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }
}
