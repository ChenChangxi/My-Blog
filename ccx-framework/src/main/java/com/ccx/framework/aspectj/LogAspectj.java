package com.ccx.framework.aspectj;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * @program: com.ccx.framework.aspectj
 * @description: 这是日志的切面(AOP)
 * @authhor: ChenChangxi
 * @create: 2019-09-28 08:42
 **/

@Component  //注入Spring容器
@Aspect     //声明这是一个切面bean
public class LogAspectj {

    /**
    *@Description: 配置切入点,所有使用了Log注解的类都成为一个切点。
    *@Param: null
    *@return: null
    *@Author: ChenChangxi
    *@date: 2019-09-28
    */
    @Pointcut("@annotation(com.ccx.common.annotation.Log)")
    public void logPointCut() {}

    /**
    *@Description: 前置通知，在进入方法前调用该方法。
    *@Param:
     * joinPoint: 切点的有关信息
    *@return: null
    *@Author: ChenChangxi
    *@date: 2019-09-28
    */
    @Before(value="logPointCut()")
    public void doBefore(JoinPoint joinPoint) {


    }

    /**
    *@Description: 环绕通知，可以同时实现前置和后置通知。
    *@Param:
     * joinPoint: 切点的有关信息
    *@return: null
    *@Author: ChenChangxi
    *@date: 2019-09-28
    */
    @Around(value="logPointCut()")
    public void doAround(JoinPoint joinPoint) {


    }

    /**
    *@Description: 异常通知，如果出现异常，将调用该方法。
    *@Param:
     * joinPoint: 切点的有关信息
     * exception: 异常的有关信息
    *@return: null
    *@Author: ChenChangxi
    *@date: 2019-09-28
    */
    @AfterThrowing(value = "logPointCut()",throwing = "exception")
    public void doAfterThrowing(JoinPoint joinPoint,Throwable exception) {


    }

    /**
    *@Description: 后置通知，出现异常将不调用这个方法。
    *@Param:
     * joinPoint: 切点的有关信息
    *@return: null
    *@Author: ChenChangxi
    *@date: 2019-09-28
    */
    @AfterReturning(value="logPointCut()")
    public void doAfterReturning(JoinPoint joinPoint) {


    }

    /**
     *@Description: 最终通知，无论是否出现异常，都会调用该方法。
     *@Param:
     * joinPoint: 切点的有关信息
     *@return: null
     *@Author: ChenChangxi
     *@date: 2019-09-28
     */
    @After(value="logPointCut()")
    public void doAfter(JoinPoint joinPoint) {


    }
}
