package com.hzz.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.util.Date;

/**
 * @author Bosco
 * @date 2021/11/12
 */
public class CookieDemo01 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //服务器，告诉你，你来的时间，把这个时间封装成为一个信件，你下次带来，我就知道你来了

        //解决中文乱码
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");

        PrintWriter out = resp.getWriter();

        //Cookie，服务器端从客户端获取的
        Cookie[] cookies = req.getCookies(); //这里返回的是数组，说明cookie可能存在多个

        //判断Cookie是否存在
        if (cookies!=null){
            //如果存在怎么办
            out.write("你上一次访问的时间");
            //out.write(URLDecoder.decode("你上一次访问的时间：","UTF-8"));
            for (int i = 0; i < cookies.length; i++) {
                Cookie cookie = cookies[i];
                //获取cookie的名字
                if (cookie.getName().equals("lastLoginTime")){
                    //获取cookie中的值
                    long lastLoginTime = Long.parseLong(cookie.getValue());
                    Date date = new Date(lastLoginTime);
                    out.write(date.toLocaleString());
                }
            }
        } else {

           out.write("这是你第一次访问本站");
        }

        //服务器端给客户端响应一个Cookie
        Cookie cookie = new Cookie("lastLoginTime", System.currentTimeMillis() + "");

        //cookie有效期为1天
        cookie.setMaxAge(24*60*60);
        //响应给客户端一个cookie
        resp.addCookie(cookie);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
