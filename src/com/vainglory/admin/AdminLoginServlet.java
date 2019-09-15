package com.vainglory.admin;

import com.vainglory.domain.User;
import com.vainglory.service.IUserService;
import com.vainglory.service.serviceImpl.UserServiceImpl;
import com.vainglory.utils.MD5Util;
import com.vainglory.web.servlet.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "AdminLoginServlet" ,value = "/adminLogin")
public class AdminLoginServlet extends BaseServlet {

    IUserService userService = new UserServiceImpl();
    public String adminLogin(HttpServletRequest request,HttpServletResponse response){
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        User user = userService.checkUserName(username);
        if (user!=null){
            System.out.println("用户名存在");
            if (MD5Util.encode(password).equals(user.getPassword())){
                System.out.println("密码正确");
                request.getSession().setAttribute("admin",user);
                return "redirect:/admin/admin.jsp";
            }
            System.out.println("密码错误");
        }else{
            System.out.println("用户名错误");
        }
        return null;
    }
}
