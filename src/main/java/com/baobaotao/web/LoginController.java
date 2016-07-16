package com.baobaotao.web;

import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.baobaotao.domain.User;
import com.baobaotao.service.UserService;
/**
 * Created by zhouzilong on 2016/7/16.
 */
// 通过@Controller注解将POJO类标注为一个Spring MVC的控制器，处理HTTP请求
// 标注为一个SpringMVC的Controller
@Controller
public class LoginController {

    @Qualifier("userService")
    @Autowired
    private UserService userService;

    // 一个控制器可以拥有多个对应不同HTTP请求路径的处理方法，通过@RequestMapping指定方法如何映射请求路径，如<2>,<3>所示
    // <2>处理/index.html 的请求
    @RequestMapping(value = "/index.html")
    public String loginPage(){
        return "login";
    }

    // 负责处理/loginCheck的请求
    // <3>处理/loginCheck.html 的请求
    @RequestMapping(value = "/loginCheck.html")
    public ModelAndView loginCheck(HttpServletRequest httpServletRequest, LoginCommand loginCommand){
        boolean isValidUser = userService.hasMatchUser(loginCommand.getUserName(), loginCommand.getPassword());
        if(!isValidUser){
            return new ModelAndView("login","error", "用户名或密码错误。");
        }else{
            User user = userService.findUserByUserName(loginCommand.getUserName());
            user.setLastIp(httpServletRequest.getRemoteAddr());
            user.setLastVist(new Date());
            userService.loginSuccess(user);
            httpServletRequest.getSession().setAttribute("user", user);
            return new ModelAndView("main");
        }
    }
}
