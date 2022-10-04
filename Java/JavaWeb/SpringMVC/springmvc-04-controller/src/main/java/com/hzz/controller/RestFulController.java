package com.hzz.controller;

import com.sun.org.glassfish.gmbal.ParameterNames;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Bosco
 * @date 2021/12/9
 */

@Controller
public class RestFulController {

    // RestFul: http://localhost:8080/add/1/3
    //映射访问路径
    //@RequestMapping(path = "/add/{a}/{b}",method = RequestMethod.GET) 简化如下：
    @GetMapping("/add/{a}/{b}")
    public String hello(@PathVariable int a, @PathVariable int b, Model model){
        //@PathVariable 注解，让方法参数的值对应绑定到一个URI模板变量上

        int result = a+b;
        //Spring MVC会自动实例化一个Model对象用于向视图中传值
        model.addAttribute("msg","结果为:"+result);
        //返回视图位置
        return "test"; //WEB-INF/jsp/test.jsp
    }

    @GetMapping("/add/t1/{a}/{b}")
    public String test2(@PathVariable int a,@PathVariable int b,Model model){

        int r= a+b;
        model.addAttribute("msg",r);

        //重定向redirect  不走试图解析器
        return "redirect:/index.jsp";
        //return "redirect:hello.do"; //hello.do为另一个请求 .
    }
}
