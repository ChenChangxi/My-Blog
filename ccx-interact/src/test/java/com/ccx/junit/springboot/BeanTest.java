package com.ccx.junit.springboot;

import com.ccx.CCXApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * @program: com.ccx.junit.springboot
 * @description: 这是我想看看SpringBoot在启动的时候创建了哪些bean
 * @authhor: ChenChangxi
 * @create: 2019-10-31 19:05
 **/

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class BeanTest {

    //ApplicationContext就是一个总的BeanFactory
    @Autowired
    private ApplicationContext applicationContext;

    @Test
    public void getBeans() {
        String[] beans=applicationContext.getBeanDefinitionNames();
        Arrays.sort(beans);
        for(String bean : beans) {
            System.out.println(bean.toString());
        }
    }
}
