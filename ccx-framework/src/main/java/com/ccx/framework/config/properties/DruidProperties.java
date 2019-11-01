package com.ccx.framework.config.properties;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @program: com.ccx.framework.config.properties
 * @description: 这是属于主从datasource共有的一些信息
 * @authhor: ChenChangxi
 * @create: 2019-09-28 17:21
 **/

@Component
@PropertySource("classpath:application-data.yml")
@ConfigurationProperties(prefix = "spring.datasource.druid")
public class DruidProperties {

    /*
    * 这里注意，@Value这个注解是用来读取外部的配置文件的，
    * 然后把文件中配置的值注入给属性
    * */
    @Value("${initial-size}")
    private int initialSize=100;

    @Value("${min-idle}")
    private int minIdle=100;

    @Value("${max-active}")
    private int maxActive=100;

    @Value("${max-wait}")
    private int maxWait=100;

    @Value("${time-between-eviction-runs-millis}")
    private int timeBetweenEvictionRunsMillis=100;

    @Value("${min-evictable-idle-time-millis}")
    private int minEvictableIdleTimeMillis=100;

    @Value("${max-evictable-idle-time-millis}")
    private int maxEvictableIdleTimeMillis=100;

    @Value("${test-on-borrow}")
    private boolean testOnBorrow=false;

    @Value("${test-while-idle}")
    private boolean testWhileIdle=true;

    @Value("${test-on-return}")
    private boolean testOnReturn=false;

    /**
    *@Description: 为数据源设置属性，配置数据源的一部分属性
    *@Param:
     * DruidDataSource
    *@return:
     * DruidDataSource
    *@Author: ChenChangxi
    *@date: 2019-09-28
    */

    public DruidDataSource setProperties(DruidDataSource dataSource) {

        dataSource.setInitialSize(initialSize);
        dataSource.setMinIdle(minIdle);
        dataSource.setMaxActive(maxActive);
        dataSource.setMaxWait(maxWait);
        dataSource.setTimeBetweenEvictionRunsMillis(minEvictableIdleTimeMillis);
        dataSource.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
        dataSource.setMaxEvictableIdleTimeMillis(maxEvictableIdleTimeMillis);
        dataSource.setTestOnBorrow(testOnBorrow);
        dataSource.setTestWhileIdle(testWhileIdle);
        dataSource.setTestOnReturn(testOnReturn);

        return dataSource;
    }
}
