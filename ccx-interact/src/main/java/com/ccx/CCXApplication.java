package com.ccx;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @program: com.ccx.interact
 * @description: 这是SpringBoot的启动类，它会自动扫描
 * 它下层的配置类，如果配置类不在它的下层，则必须用compo
 * nentScan来进行扫包。
 * @authhor: ChenChangxi
 * @create: 2019-09-26 17:59
 **/

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@MapperScan(basePackages = {"com.ccx.system.mapper", "com.ccx.junit.mybatis.mapper"})
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
