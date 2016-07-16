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
@Controller
public class LoginController {

    @Qualifier("userService")
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/index.html")
    public String loginPage(){
        return "login";
    }

    @RequestMapping(value = "/loginCheck.html")
    public ModelAndView loginCheck(HttpServletRequest httpServletRequest, LoginCommand loginCommand){

    }
}
