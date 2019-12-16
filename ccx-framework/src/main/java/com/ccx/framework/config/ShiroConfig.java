package com.ccx.framework.config;

import com.ccx.common.constant.LoginConstants;
import com.ccx.framework.shiro.matcher.UserMatcher;
import com.ccx.framework.shiro.realm.UserRealm;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.mgt.DefaultSessionManager;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @program: com.ccx.framework.config
 * @description: 这是权限控制框架Shiro的配置工厂
 * @authhor: ChenChangxi
 * @create: 2019-11-11 20:23
 **/

@Configuration
public class ShiroConfig {


    //将密码验证器注入容器
    @Bean("UserMatcher")
    public UserMatcher userMatcher() {
        return new UserMatcher();
    }

    @Bean("SimpleCookie")
    public SimpleCookie simpleCookie() {
        SimpleCookie simpleCookie=new SimpleCookie();
        simpleCookie.setName("cookie");   //不设置这个的话servlet和shiro默认的cookie会发生冲突
        return simpleCookie;
    }

    //默认的会话管理器
    @Bean("SessionManager")
    public DefaultWebSessionManager sessionManager(@Qualifier("SimpleCookie")SimpleCookie simpleCookie) {
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
        //去掉JSESSIONID
        sessionManager.setSessionIdUrlRewritingEnabled(false);
        sessionManager.setSessionIdCookie(simpleCookie);
        sessionManager.setSessionIdCookieEnabled(true);
        return sessionManager;
    }

    //将自定义的realm注入容器中
    @Bean("UserRealm")
    public UserRealm userRealm(@Qualifier("UserMatcher") UserMatcher userMatcher) {
        UserRealm userRealm = new UserRealm();
        userRealm.setCredentialsMatcher(userMatcher);
        return userRealm;
    }

    //自定义安全管理器
    @Bean("SecurityManager")
    public SecurityManager securityManager(@Qualifier("UserRealm") UserRealm userRealm,
                                           @Qualifier("SessionManager")SessionManager sessionManager) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(userRealm);
        securityManager.setSessionManager(sessionManager);
        return securityManager;
    }

    /**
    *@Description: 配置拦截器，需要定义哪些请求会被权限拦截器拦截并进行认证，
     * 这和SpringMVC的拦截器是一样的，它只定义了哪些请求需要什么样的权限验证
     * 但具体的验证信息我们需要在每一个Controller的注解中给出。有四个验证类型
     *
    *@Author: ChenChangxi
    *@date: 2019-12-05
    */

    //Shiro的拦截器
    @Bean("ShiroFilter")
    public ShiroFilterFactoryBean shiroFilter(@Qualifier("SecurityManager")SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);

        Map<String,String> filterChain = new LinkedHashMap<String, String>();

        filterChain.put("/logout","logout");    //Shiro会自动帮你重定向到login页面
        filterChain.put("/test/**","anon");  //静态资源不进行拦截，这个地方别人可以偷你的前端页面
        filterChain.put("/**","authc");   //对于前面都不匹配的请求，需要验证，必须放在链中的最后

        //默认的登陆界面
        shiroFilterFactoryBean.setLoginUrl(LoginConstants.LOGIN);
        shiroFilterFactoryBean.setSuccessUrl(LoginConstants.QUERY);
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChain);

        return shiroFilterFactoryBean;
    }

    /**
    *@Description: 以下两个方法是开启shiro注解的，在方法粒度下实现角色和权限
     * 的更为精细的控制，它是注解在某个具体的Controller的，如@RequiresRoles
     * (value={“admin”, “user”}, logical= Logical.AND)的意思就是请求的这
     * 个用户必须具有admin和user角色才允许访问这个请求。而角色的获取就是Shiro
     * 框架自己要做的事了。它可以很优雅的进行权限验证，这就是框架的作用，本质上
     * 是各种设计模式和反射的运用。
    *@Author: ChenChangxi
    *@date: 2019-12-05
    */

    //开启Shiro注解支持
    @Bean
    public DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator(){
        DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        advisorAutoProxyCreator.setProxyTargetClass(true);
        return advisorAutoProxyCreator;
    }

    //开启AOP注解支持
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(
            @Qualifier("SecurityManager") SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }
}
