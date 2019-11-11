package com.ccx.junit.mybatis.service;

import com.ccx.common.entity.BaseEntity;
import com.ccx.junit.mybatis.mapper.TestMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: com.ccx.junit
 * @description: 这是MyBatis的的单元测试，这里有个说大不大说小也不小
 * 的坑，困扰了我好几天，就是dao和mapper.xml文件的映射问题，我们在app
 * lication.yml文件中配置了mapper.xml文件的路径，但这个配置对于在test
 * 下面的resources目录并不起作用，dao接口还是会查找和它包路径相同的配
 * 置文件。还有就是千万不能用@MaybatisTest这个注解，这个也困扰了我一段
 * 时间，最后看看它的文档，结合网上的博客才发现这个注解是不会加载resources
 * 下面的配置文件的，因此jdbc路径，数据源，sqlsessionfactory这些都不会
 * 初始化。
 * @authhor: ChenChangxi
 * @create: 2019-09-22 21:36
 **/

//这里注意，你没有用MyBatis默认的数据源，所以一定要排除，
//让SpringBoot去加载你自己配置的数据源，否则它会在data
//source下面去找url，那肯定是找不到的
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) //用你定义的数据源
public class TestService {

    @Autowired
    private TestMapper testMapper;

    @Test
    @Rollback(true)
    public void insert() {
        Map<String,String> map=new HashMap<String, String>();
        map.put("ping","success");
        map.put("id","1");
        testMapper.insert(map);
        System.out.println("success");
    }

    @Test
    @Rollback(true)
    public void update() {
        BaseEntity baseEntity = new BaseEntity();
        Map<String,String> map=new HashMap<String, String>();
        map.put("ping","fail");
        map.put("id","1");
        testMapper.update(map);
        System.out.println("success");
    }

    @Test
    @Rollback(true)
    public void select() {
        BaseEntity baseEntity=testMapper.select(1);
        System.out.println(baseEntity.getId());
        System.out.println(baseEntity.getPing());
        System.out.println("success");
    }

    @Test
    @Rollback(true)
    public void delect() {
        testMapper.delete(1);
        System.out.println("success");
    }
}
