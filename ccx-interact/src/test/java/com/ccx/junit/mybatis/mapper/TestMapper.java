package com.ccx.junit.mybatis.mapper;

import com.ccx.common.entity.BaseEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * @program: com.ccx.junit.MyBatis
 * @description: 这是测试的Mybatis的映射接口
 * @authhor: ChenChangxi
 * @create: 2019-10-31 10:55
 **/


public interface TestMapper {

    public String select(int id);

    public void insert(BaseEntity baseEntity);

    public void delete(int id);

    public void update(BaseEntity baseEntity);
}
