package com.vainglory.admin;

import com.alibaba.fastjson.JSON;
import com.vainglory.domain.User;
import com.vainglory.service.IUserService;
import com.vainglory.service.serviceImpl.UserServiceImpl;
import com.vainglory.web.servlet.BaseServlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "UserManageServlet" ,value = "/userManage")
public class UserManageServlet extends BaseServlet {

    private IUserService userService = new UserServiceImpl();

    public void showAllUser(HttpServletRequest request,HttpServletResponse response){
        response.setContentType("application/json;charset=utf-8");
        List<User> users = userService.queryAll(1);
        String strUsers = JSON.toJSONString(users);
        try {
            System.out.println(strUsers);
            response.getWriter().println(strUsers);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String deleteUser(HttpServletRequest request,HttpServletResponse response){
        String id = request.getParameter("id");
        boolean result = userService.delete(Integer.parseInt(id));
        if (result){
            return "redirect:/admin/userList.jsp";
        }
        return null;
    }
}
