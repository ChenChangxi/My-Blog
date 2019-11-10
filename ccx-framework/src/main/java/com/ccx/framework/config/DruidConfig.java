package com.ccx.framework.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import com.ccx.framework.config.properties.DruidProperties;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;

import javax.sql.DataSource;

/**
 * @program: com.ccx.framework.config
 * @description: 配置阿里的Druid数据源,由于禁用了默认的MyBatis
 * 对数据源的配置，因此除了配置主从数据源之外，还必须要配置sqlsession
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
@PropertySource("classpath:application-data.yml")
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

    @Bean(name = "masterDataSource")
    @Primary
    @ConfigurationProperties("spring.datasource.druid.master")
    public DataSource injectMasterDataSource(DruidProperties druidProperties) {

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

    @Bean(name = "slaveDataSource")
    @ConfigurationProperties("spring.datasource.druid.slave")
    @ConditionalOnProperty(prefix = "spring.datasource.druid.slave",name="enabled",havingValue = "true")
    public DataSource injectSlaveDataSource(DruidProperties druidProperties) {

        DruidDataSource dataSource = DruidDataSourceBuilder.create().build();
        return druidProperties.setProperties(dataSource);
    }

    @Primary
    @Bean(name = "masterSqlSessionFactory")
    public SqlSessionFactory masterSessionFactory(@Qualifier("masterDataSource") DataSource dataSource)
    throws Exception{
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        return bean.getObject();
    }

    @Bean(name = "slaveSqlSessionFactory")
    @ConditionalOnProperty(prefix = "spring.datasource.druid.slave",name="enabled",havingValue = "true")
    public SqlSessionFactory slaveSessionFactory(@Qualifier("slaveDataSource") DataSource dataSource)
    throws Exception{
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        return bean.getObject();
    }

    @Primary
    @Bean(name = "masterSqlSessionTemplate")
    public SqlSessionTemplate masterSqlSessionTemplate(@Qualifier("masterSqlSessionFactory")
                                                       SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

    @Bean(name = "slaveSqlSessionTemplate")
    @ConditionalOnProperty(prefix = "spring.datasource.druid.slave",name="enabled",havingValue = "true")
    public SqlSessionTemplate slaveSqlSessionTemplate(@Qualifier("slaveSqlSessionFactory")
                                                      SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
