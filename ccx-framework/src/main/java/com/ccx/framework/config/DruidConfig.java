package com.ccx.framework.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import com.ccx.framework.config.properties.DruidProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * @program: com.ccx.framework.config
 * @description: 配置阿里的Druid数据源
 * @authhor: ChenChangxi
 * @create: 2019-09-28 11:09
 **/

/**
 * @Configuration: 这个注解会启动Spring容器，相当于<beans>标签
 * @Bean： 这个注解声明把函数的返回值作为一个bean注入容器中，相当于<bean>标签
 * @ConfigurationProperties: 外部属性添加到bean中，它可以批量添加，这是它和
 * '@value'注解的区别
 * @ConditionalPropertise: 这个注解可以控制bean是否被注入，只有name的值为t
 * rue时，这个bean才会被注入到Spring容器中
 */

@Configuration
public class DruidConfig {

    /**
    *@Description: 将主数据源注入Spring容器
    *@Param:
     * DruidProperties
    *@return:
     * DruidProperties
    *@Author: ChenChangxi
    *@date: 2019-09-28
    */

    @Bean
    @ConfigurationProperties("spring.datasource.druid.master")
    public DataSource injectMasterDataSource(DruidProperties druidProperties) {  //这里还不太懂，Spring会自己初始化参数吗？

        DruidDataSource dataSource = DruidDataSourceBuilder.create().build();
        return druidProperties.setProperties(dataSource);
    }

    /**
    *@Description: 将从数据源注入Spring容器
    *@Param:
     * DruidProperties
    *@return:
     * DataSource
    *@Author: ChenChangxi
    *@date: 2019-09-28
    */

    @Bean
    @ConfigurationProperties("spring.datasource.druid.slave")
    @ConditionalOnProperty(prefix = "spring.datasource.druid.slave",name="enabled",havingValue = "true")
    public DataSource injectSlaveDataSource(DruidProperties druidProperties) {

        DruidDataSource dataSource = DruidDataSourceBuilder.create().build();
        return druidProperties.setProperties(dataSource);
    }
}
