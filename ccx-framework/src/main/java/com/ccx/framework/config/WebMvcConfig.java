package com.ccx.framework.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.*;

/**
 * @program: com.ccx.framework.config
 * @description: SpringMVC访问静态资源，这里要注意非常重要的一点
 * SpringBoot整合SpringMVC的时候，默认是可以访问静态资源的，这与
 * Spring整合SpringMVC的时候是不同的，但是SpringBoot2.0以后，默
 * 认的静态资源是在META-INF/resources/resources/static/public
 * 里面的，thmeleaf模版引擎是以html为基础的，同时支持动态和静态访问
 * 这里我们默认它是静态的(因为它支持浏览器直接访问)，因此想要在templ
 * ates里面访问静态资源，就必须对SpringMVC做一定的配置。
 * @authhor: ChenChangxi
 * @create: 2019-11-11 19:53
 **/

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    //跨域访问问题
    public void addCorsMappings(CorsRegistry registry) {

    }

    //静态资源路径问题
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/imgs/")
                .addResourceLocations("classpath:static/imgs/");
        registry.addResourceHandler("/patterns/")
                .addResourceLocations("classpath:static/patterns/");
        registry.addResourceHandler("/ajax/libs/**")
                .addResourceLocations("classpath:static/ajax/libs/");
    }

    //添加拦截器
    public void addInterceptors(InterceptorRegistry registry) {

    }
}
