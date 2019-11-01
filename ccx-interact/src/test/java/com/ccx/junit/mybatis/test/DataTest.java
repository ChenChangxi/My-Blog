package com.ccx.junit.mybatis.test;

import com.ccx.common.entity.BaseEntity;
import com.ccx.junit.mybatis.mapper.TestMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;

/**
 * @program: com.ccx.junit
 * @description: 这是MyBatis的的单元测试
 * @authhor: ChenChangxi
 * @create: 2019-09-22 21:36
 **/

//这里注意，你没有用MyBatis默认的数据源，所以一定要排除，
//让SpringBoot去加载你自己配置的数据源，否则它会在data
//source下面去找url，那肯定是找不到的
@MybatisTest
@RunWith(SpringJUnit4ClassRunner.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) //用你定义的数据源
public class DataTest {

    @Autowired
    private TestMapper testMapper;

    @Test
    @Rollback(true)
    public void insert() {
        BaseEntity baseEntity = new BaseEntity();
        baseEntity.setId(1);
        baseEntity.setPing("ping");
        testMapper.insert(baseEntity);
        System.out.println("success");
    }

    @Test
    @Rollback(true)
    public void update() {
        BaseEntity baseEntity = new BaseEntity();
        baseEntity.setId(1);
        baseEntity.setPing("pang");
        testMapper.update(baseEntity);
        System.out.println("success");
    }

    @Test
    @Rollback(true)
    public void select() {
        String ping=testMapper.select(1);
        System.out.println(ping);
        System.out.println("success");
    }

    @Test
    @Rollback(true)
    public void delect() {
        testMapper.delete(1);
        System.out.println("success");
    }
}
