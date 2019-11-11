package com.ccx.junit.springmvc;

import com.ccx.junit.mybatis.mapper.TestMapper;
import com.ccx.junit.mybatis.service.TestService;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Controller;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @program: com.ccx.junit.springmvc
 * @description: 进行Spring整合SpringMVC的单元测试,写的controller
 * @authhor: ChenChangxi
 * @create: 2019-11-10 14:43
 **/


@Controller
public class TestController {

    private static final String SUCCESS="/test/success";

    @Autowired
    private TestMapper testMapper;

    /**
    *@Description: 这里注意，@RequestBody这个注解接受的是一个json字符串
     * 然后把它转化为所需要的List或者Map类型
    *@Param: Map<String,String> map
    *@return: ModelAndView
    *@Author: ChenChangxi
    *@date: 2019-11-11
    */
    @RequestMapping("/insert")
    @ResponseBody
    public ModelAndView insert(@RequestBody Map<String,String> map) {
        Map<String,String> request=new HashMap<String, String>();
        request.put("id",map.get("account"));
        request.put("ping",map.get("password"));
        testMapper.insert(request);
        ModelAndView model = new ModelAndView(SUCCESS);
        Map<String,String> result=new HashMap<String, String>();
        result.put("test","success");
        result.put("ok","Successfully Testing SpringMVC+Thymeleaf");
        model.addAllObjects(result);
        return model;
    }
}
