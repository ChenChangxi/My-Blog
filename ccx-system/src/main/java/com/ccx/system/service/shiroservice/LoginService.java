package com.ccx.system.service.shiroservice;

import com.ccx.common.entity.UserEntity;
import org.springframework.stereotype.Service;

/**
 * @program: com.ccx.system.service.shiroservice
 * @description: 这是验证登陆信息的service接口
 * @authhor: ChenChangxi
 * @create: 2019-12-04 23:01
 **/

@Service
public interface LoginService {

    public UserEntity getUserByName(String username);


}
