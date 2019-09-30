package com.ccx.framework.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import com.ccx.framework.shiro.realm.UserRealm;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @program: com.ccx.framework.config
 * @description: 配置Shiro安全框架
 * @authhor: ChenChangxi
 * @create: 2019-09-28 23:11
 **/

@Configuration
public class ShiroConfig {


    @Value("${shiro.}")








    /**
    *@Description: 将userrealm注入Spring容器。
    *@Param:
     * param:参数
    *@return:
     * userRealm:UserRealm实体类的对象，需要自己实现。
    *@Author: ChenChangxi
    *@date: 2019-09-29
    */
    @Bean
    public UserRealm userRealm() {

        UserRealm userRealm = new UserRealm();



        return userRealm;
    }

    /**
    *@Description: shiro利用ehcache缓存管理器。
    *@Param:
     * param:参数
    *@return:
     * EhCacheManager
    *@Author: ChenChangxi
    *@date: 2019-09-29
    */
    @Bean
    public EhCacheManager ehCacheManager() {

        EhCacheManager ehCacheManager = new EhCacheManager();



        return ehCacheManager;
    }

    /**
    *@Description: 配置cookie，使cookie能够记住用户的一些信息
    *@Param:
     * param:参数
    *@return:
     * CookieRememberMeManager
    *@Author: ChenChangxi
    *@date: 2019-09-30
    */

    @Bean
    public CookieRememberMeManager rememberMeManager() {

        CookieRememberMeManager rememberMeManager = new CookieRememberMeManager();

        return rememberMeManager;
    }

    /**
    *@Description: shiro框架的核心，它负责管理realm，
     * session，cache，rememberme等所有组件。
    *@Param:
     * param:参数
    *@return:
     * 向上转型为SecurityManager实体类的对象。
    *@Author: ChenChangxi
    *@date: 2019-09-29
    */
    @Bean
    public SecurityManager securityManager() {

        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager(); // 这个不用自己实现，shiro已经实现好了



        return securityManager;

    }

    /**
    *@Description: 这和SpringMVC的过滤器其实是一个道理，我们所
     * 进行的权限管理是基于数据库中的数据的，对于静态页面的访问则
     * 采取匿名的方式进行。shiro会对所有前端的请求进行拦截，然后
     * 进行权限检验，下面配置的就是不会拦截的"静态资源"，以及一旦
     * 权限验证失败会重定向到哪个页面。
    *@Param:
    *@return:
    *@Author: ChenChangxi
    *@date: 2019-09-29
    */
    @Bean
    public ShiroFilterFactoryBean filterFactoryBean() {

        ShiroFilterFactoryBean filterFactoryBean = new ShiroFilterFactoryBean();



        return filterFactoryBean;
    }

    /**
    *@Description: 这是为了在模版引擎thymeleaf中使用shiro框架
    *@Param:
     * param:参数
    *@return:
     * ShiroDialect实体类的对象
    *@Author: ChenChangxi
    *@date: 2019-09-29
    */
    @Bean
    public ShiroDialect shiroDialect() {

        return new ShiroDialect();
    }

    /**
    *@Description: 开启shiro注解功能，所有在添加shiro注解的
     * controller在拦截ajax请求的时候都会进行shiro权限验证
    *@Param:
     * param:参数
    *@return:
     * AuthorizationAttributeSourceAdvisor:
    *@Author: ChenChangxi
    *@date: 2019-09-29
    */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor() {

        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();



        return authorizationAttributeSourceAdvisor;
    }
}
