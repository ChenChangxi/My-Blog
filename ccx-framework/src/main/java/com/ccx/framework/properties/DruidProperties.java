package com.ccx.framework.properties;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @program: com.ccx.framework.properties
 * @description: 这是属于主从datasource共有的一些信息
 * @authhor: ChenChangxi
 * @create: 2019-09-28 17:21
 **/

@Configuration
public class DruidProperties {

    /*
    * 这里注意，@Value这个注解是用来读取外部的配置文件的，
    * 然后把文件中配置的值注入给属性
    * */
    @Value("${spring.datasource.druid.initial-size}")
    private int initialSize;

    @Value("${spring.datasource.druid.min-idle}")
    private int minIdle;

    @Value("${spring.datasource.druid.max-active}")
    private int maxActive;

    @Value("${spring.datasource.druid.max-wait}")
    private int maxWait;

    @Value("${spring.datasource.druid.time-between-eviction-runs-millis}")
    private int timeBetweenEvictionRunsMillis;

    @Value("${spring.datasource.druid.min-evictable-idle-time-millis}")
    private int minEvictableIdleTimeMillis;

    @Value("${spring.datasource.druid.max-evictable-idle-time-millis}")
    private int maxEvictableIdleTimeMillis;

    @Value("${spring.datasource.druid.test-on-borrow}")
    private boolean testOnBorrow;

    @Value("${spring.datasource.druid.test-while-idle}")
    private boolean testWhileIdle;

    @Value("${spring.datasource.druid.test-on-return}")
    private boolean testOnReturn;

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
