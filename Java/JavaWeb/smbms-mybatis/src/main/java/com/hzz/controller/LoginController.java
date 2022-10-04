package com.hzz.controller;

import com.hzz.pojo.User;
import com.hzz.service.user.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

/**
 * @author Bosco
 * @date 2021/12/20
 */
@Controller
@RequestMapping("/user")
public class LoginController {

    @Resource
    private UserService userService;

    @GetMapping("/login")
    public String login(){
        return "redirect:/login.jsp";
    }

    //用户登录
    @PostMapping("/login")
    public String login(Model model, HttpSession session, @RequestParam(required = true) String userCode,@RequestParam(required = true) String userPassword){
        User loginUser = userService.login(userCode);
        if( loginUser != null){
            if (loginUser.getUserPassword().equals(userPassword)){
                // 向session记录用户身份信息
                session.setAttribute("userLoginInfo",loginUser.getUserCode());
                model.addAttribute("user",loginUser);
                session.setAttribute("error",userCode);
                return "";
            }else {
                model.addAttribute("error","密码错误");
                return "redirect:/login.jsp";
            }
        }else {
            model.addAttribute("error","账号错误");
            return "redirect:/login";
        }
    }

}
