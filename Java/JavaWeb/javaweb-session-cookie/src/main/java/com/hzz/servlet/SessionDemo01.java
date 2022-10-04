package com.hzz.servlet;

import com.hzz.pojo.Person;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author Bosco
 * @date 2021/11/13
 */
public class SessionDemo01 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //解决乱码问题
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");

        //得到session
        HttpSession session = req.getSession();

        //给Session中存东西
        session.setAttribute("name",new Person("黄宗钊",18));

        //获取Session的ID
        String sessionId = session.getId();

        //判断Session的ID
        if (session.isNew()){
            resp.getWriter().write("session创建成功，ID:"+sessionId);
        } else {
            resp.getWriter().write("session以及在服务器中存在了,ID:"+sessionId);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
