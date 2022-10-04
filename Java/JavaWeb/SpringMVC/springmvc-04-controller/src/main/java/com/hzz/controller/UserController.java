package com.hzz.controller;

import com.hzz.pojo.User;
import com.sun.xml.internal.ws.api.ha.StickyFeature;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.jws.WebParam;

/**
 * @author Bosco
 * @date 2021/12/9
 */

@Controller
@RequestMapping("/user")
public class UserController {

    //localhost:8080/user/t1?username=xxx;   @RequestParam("username") : username提交的域的名称
    @GetMapping("/t1")
    public String test1(@RequestParam("username") String name, Model model){
        //1.接收前端参数
        System.out.println("接收到前端的参数为："+name);
        //2.将返回的结果传递给前端 ，用Model
        model.addAttribute("msg",name);
        //3.视图跳转
        return "test";
    }


    //前端接收的是一个对象：id,name,age

    /*
    1.接收前端用户传递的参数，判断参数的名字，假设名字直接在方法上，可以直接使用
    2.假设传递的是一个对象User，匹配User对象中的字段名；如果名字一致则ok，否则匹配不到
    */

    //使用对象的话，前端传递的参数名和对象名必须一致，否则就是null
    @GetMapping("/t2")
    public String test2(User user){
        System.out.println(user);
        return "test";
    }

    //ModelMap 和Model差不多的
    @GetMapping("/t3")
    public String test3(@RequestParam("name") String name,Model model){
        model.addAttribute("msg",name);
        return "test";
    }
}
