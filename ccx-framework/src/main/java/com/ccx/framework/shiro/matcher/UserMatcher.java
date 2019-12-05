package com.ccx.framework.shiro.matcher;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;
import org.apache.shiro.crypto.hash.Sha384Hash;

/**
 * @program: com.ccx.framework.shiro.matcher
 * @description: 它是密码验证的器，这里我们用散列算法对密码进行加密和验证
 * @authhor: ChenChangxi
 * @create: 2019-12-04 23:10
 **/

public class UserMatcher extends SimpleCredentialsMatcher {

    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {

        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) token;
        String password=encrypt(String.valueOf(usernamePasswordToken.getPassword()));
        String realPassword=(String)info.getCredentials();
        return equals(password,realPassword);
    }
    
    /**
    *@Description:采用哈希散列算法加密，在密码保存到数据库中时，也要使用同
     * 的方法进行加密，这个过程是不可恢复的，也就是说你可以由密码得到哈希的值
     * 但是你无法根据哈希值拿到真正的密码字符串
    *@Author: ChenChangxi
    *@date: 2019-12-05
    */
    private String encrypt(String password) {

        String sha384Hex=new Sha384Hash(password).toBase64();
        return sha384Hex;
    }
}
