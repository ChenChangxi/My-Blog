package com.ccx.junit.mybatis.mapper;

import com.ccx.common.entity.BaseEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * @program: com.ccx.junit.MyBatis
 * @description: 这是测试的Mybatis的映射接口
 * @authhor: ChenChangxi
 * @create: 2019-10-31 10:55
 **/

public interface TestMapper {

    public BaseEntity select(int id);

    public void insert(Map<String,String> map);

    public void delete(int id);

    public void update(Map<String,String> map);
}
