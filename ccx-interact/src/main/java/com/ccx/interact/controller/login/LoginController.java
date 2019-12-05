package com.ccx.interact.controller.login;

import com.ccx.common.constant.LoginConstants;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * @program: com.ccx.system.controller.login
 * @description: 这是用户登录的controller，通过shiro来验证用户登陆
 * 信息和登陆权限和登陆信息是否正确，通过subject与token的login关系来
 * 验证，这是shiro来保护项目的一个抽象的概念。
 * @authhor: ChenChangxi
 * @create: 2019-12-04 23:22
 **/

@Controller
public class LoginController {

    @RequestMapping("login")
    public ModelAndView login(@RequestParam("username") String username,
                              @RequestParam("password") String password) {
        UsernamePasswordToken token=new UsernamePasswordToken();
        Subject subject = SecurityUtils.getSubject();
        try {

            subject.login(token);
        } catch(IncorrectCredentialsException pae) { //密码错误异常

            ModelAndView model=new ModelAndView(LoginConstants.ERROR);
            model.addObject("message","您输入的密码有错误");
            return model;
        } catch(UnknownAccountException une) { //未知用户名异常

            ModelAndView model=new ModelAndView(LoginConstants.ERROR);
            model.addObject("message","您输入的用户名不存在");
            return model;
        } catch(ExcessiveAttemptsException exe) { //登陆过多异常

            ModelAndView model=new ModelAndView(LoginConstants.ERROR);
            model.addObject("message","您的登陆次数过多");
        }



        return new ModelAndView("success");
    }
}
