package com.ccx.junit.springmvc;

import com.alibaba.fastjson.JSON;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.client.match.MockRestRequestMatchers;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: com.ccx.junit.springmvc
 * @description: 利用MockMvc来对web项目进行单元测试
 * @authhor: ChenChangxi
 * @create: 2019-11-11 11:07
 **/


@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
public class TestMockMvc {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @Before
    public void setUp() {

        this.mockMvc= MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    public void test() throws Exception {
        Map<String,String> map = new HashMap<String, String>();
        map.put("account","1");
        map.put("password","QTSS.a000");
        MockHttpServletRequestBuilder request=MockMvcRequestBuilders
                .post("/insert")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(JSON.toJSONString(map));
        MvcResult result=mockMvc.perform(request)
                .andDo(MockMvcResultHandlers.print()) //打印出请求的详细信息
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        System.out.println(result.getResponse().getContentAsString());
    }
}
