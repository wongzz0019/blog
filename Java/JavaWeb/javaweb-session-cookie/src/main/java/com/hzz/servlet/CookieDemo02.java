package com.hzz.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *删除Cookie
 */

public class CookieDemo02 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 创建一个cookie，名字必须要和删除的名字一致
        Cookie cookie = new Cookie("lastLoginTime", System.currentTimeMillis() + "");
        //有效期设置为0，立马过期
        cookie.setMaxAge(0);
        //服务器响应一个cookie
        resp.addCookie(cookie);



    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
