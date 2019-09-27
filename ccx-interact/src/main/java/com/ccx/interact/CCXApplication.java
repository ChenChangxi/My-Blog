package com.ccx.interact;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @program: com.ccx.interact
 * @description: 这是SpringBoot的启动类
 * @authhor: ChenChangxi
 * @create: 2019-09-26 17:59
 **/

@SpringBootApplication
public class CCXApplication {

    public static void main(String[] args) {
        /**
        *@Description: 启动SpringBoot内置的Tomcat
        *@Param: [args]
        *@return: void
        *@Author: ChenChangxi
        *@date: 2019-09-27
        */
        SpringApplication.run(CCXApplication.class,args);
    }
}
