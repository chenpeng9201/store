package com.itheima.web;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;

public class BaseServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //使用反射动态调用本类中的方法
        String method = req.getParameter("method");
        resp.setContentType("text/html;charset=utf-8");
        try {
            Method m = this.getClass().getDeclaredMethod(method,HttpServletRequest.class,HttpServletResponse.class);
            m.invoke(this,req,resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
