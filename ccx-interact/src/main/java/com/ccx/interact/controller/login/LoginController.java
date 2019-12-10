package com.ccx.interact.controller.login;

import com.ccx.common.constant.LoginConstants;
import com.ccx.common.entity.UserEntity;
import com.ccx.system.service.shiroservice.LoginService;
import org.apache.catalina.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Set;

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

    @Autowired
    private LoginService loginService;

    @RequestMapping("login")
    public ModelAndView login(@RequestParam("username") String username,
                              @RequestParam("password") String password) {
        UsernamePasswordToken token=new UsernamePasswordToken(username,password);
        Subject subject = SecurityUtils.getSubject();

        System.out.println("============login===========");

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
        //用户信息保存在会话中，用户访问服务器，时刻有效
        UserEntity user = loginService.getUserByUserName(username);
        subject.getSession().setAttribute("user",user);
        return new ModelAndView(LoginConstants.QUERY);
    }

    @RequestMapping("query")
    @RequiresRoles(value={"admin"})
    @RequiresPermissions(value={"permit"})
    public ModelAndView index() {
        Subject subject = SecurityUtils.getSubject();
        UserEntity user=(UserEntity)subject.getSession().getAttribute("user");
        ModelAndView model = new ModelAndView(LoginConstants.SUCCESS);
        model.addObject("username",user.getUserName());
        model.addObject("password",user.getPassWord());
        return model;
    }

    @RequestMapping("register")
    public ModelAndView register(@RequestParam("username") String username,
                                 @RequestParam("password") String password) {
        UserEntity user = new UserEntity();
        user.setUserName(username);
        user.setPassWord(password);
        loginService.insertUser(user);
        return new ModelAndView(LoginConstants.LOGIN);
    }
}
