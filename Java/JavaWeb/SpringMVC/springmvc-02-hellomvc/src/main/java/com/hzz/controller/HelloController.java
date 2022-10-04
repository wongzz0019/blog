package com.hzz.controller;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Bosco
 * @date 2021/12/8
 */
public class HelloController implements Controller {
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ModelAndView mv = new ModelAndView();

        //业务代码
        String result = "HelloServlet";
        mv.addObject("msg",result);

        //试图跳转
        mv.setViewName("test");

        return mv;
    }
}
