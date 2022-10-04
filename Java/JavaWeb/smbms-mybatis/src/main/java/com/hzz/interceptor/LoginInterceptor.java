package com.hzz.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author Bosco
 * @date 2021/12/19
 */
public class LoginInterceptor implements HandlerInterceptor {
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //什么情况下放行
        HttpSession session = request.getSession();
        //session不为空
        if (session.getAttribute("userLoginInfo") != null){
            return true;
        }else if (request.getRequestURI().contains("/login")){
            //登录页面也放行
            return true;
        }else {
            request.getRequestDispatcher("login.jsp").forward(request,response);
            return false;
        }

    }

    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
