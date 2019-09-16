package com.vainglory.web.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;

public class BaseServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("utf-8");
        //判断用户当前行为
        String option = request.getParameter("op");
        System.out.println(option);
        //反射调用
        try {
            Method method = this.getClass().getMethod(option, HttpServletRequest.class, HttpServletResponse.class);
            String url = (String) method.invoke(this,request,response);
            if (url != null && url.trim().length() != 0){
                //重定向
                if(url.startsWith("redirect:")){
                    response.sendRedirect(request.getContextPath()+url.split(":")[1]);
                }else {//请求转发
                    request.getRequestDispatcher(url).forward(request,response);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
