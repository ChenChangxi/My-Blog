package com.ccx.common.annotation;

import java.lang.annotation.*;

/**
 * @program: com.ccx.common.annotation
 * @description: AOP中，所有使用该注解的类都成为一个"切点"
 * @authhor: ChenChangxi
 * @create: 2019-09-28 10:37
 **/

@Target(ElementType.METHOD) // 我们是对方法进行切面的。
@Retention(RetentionPolicy.RUNTIME) // 这是一个工作在runtime级别的注解
@Documented    // 此注解包含在Javadoc中
public @interface Log {


}
