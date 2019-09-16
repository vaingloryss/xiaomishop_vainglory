package com.vainglory.web.servlet;

import com.vainglory.domain.User;
import com.vainglory.service.IUserService;
import com.vainglory.service.serviceImpl.UserServiceImpl;
import com.vainglory.utils.MD5Util;
import com.vainglory.utils.StringUtils;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.Base64;

@WebServlet(name = "UserServlet",value = {"/userServlet"})
public class UserServlet extends BaseServlet {

    private IUserService userService = new UserServiceImpl();

    //用户注册
    public String register(HttpServletRequest request,HttpServletResponse response)throws Exception{
        User user = new User();
        BeanUtils.populate(user,request.getParameterMap());
        String repassword = request.getParameter("repassword");
        if (StringUtils.isEmpty(user.getUsername())){
            request.setAttribute("registerMsg","用户名不能为空");
            return "/register.jsp";
        }
        if (StringUtils.isEmpty(user.getPassword())){
            request.setAttribute("registerMsg","密码不能为空");
            return "/register.jsp";
        }
        if (!user.getPassword().equals(repassword)){
            request.setAttribute("registerMsg","两次密码不一致");
            return "/register.jsp";
        }
        if (StringUtils.isEmpty(user.getEmail())){
            request.setAttribute("registerMsg","邮箱不能为空");
            return "/register.jsp";
        }
        userService.register(user);
        return "redirect:/registerSuccess.jsp";
    }

    public String login(HttpServletRequest request,HttpServletResponse response){
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String loginMsg = "";

        User user = userService.checkUserName(username);
        if (user!=null){
            //自动登录 无需加密 因为 cookie中存储的密码 是加密过的
            //如果不是自动登录 需要 对密码进行 MD5加密转换 才能和 User中的密码进行匹配
            if (user.getPassword().equals(MD5Util.encode(password))){
                //判断是否选中两周内自动登录
                String auto = request.getParameter("auto");
                if (auto!=null){
                    Cookie[] cookies = request.getCookies();
                    boolean r = false;
                    for (Cookie cookie : cookies) {
                        if ("autoUser".equals(cookie.getName())){
                            r = true;
                            break;
                        }
                    }
                    if (!r){
                        Cookie cookie = new Cookie("autoUser",user.getUsername()+":"+user.getPassword());
                        cookie.setPath("/");
                        cookie.setMaxAge(14*24*60*60);
                        response.addCookie(cookie);
                    }
                }
            }else {
                loginMsg = "密码不正确";
            }
        }else {
            loginMsg="用户名不正确";
        }
        if (loginMsg.length()==0){
            request.getSession().setAttribute("user",user);
            return "redirect:/index.jsp";
        }else{
            request.getSession().setAttribute("loginMsg",loginMsg);
            return "redirect:/login.jsp";
        }
    }

    public String logOut(HttpServletRequest request,HttpServletResponse response){
        Cookie cookie = new Cookie("autoUser","");
        cookie.setPath("/");
        cookie.setMaxAge(0);
        HttpSession session = request.getSession();
        session.removeAttribute("user");
        session.invalidate();
        return "redirect:/login.jsp";
    }
    public String checkUserName(HttpServletRequest request,HttpServletResponse response) throws Exception{
        String username = request.getParameter("username");
        if(username==null||username.trim().length()==0){
            return null;
        }
        IUserService userService=new UserServiceImpl();
        User user = userService.checkUserName(username);
        if(user!=null){
            response.getWriter().write("1");
        }else{
            response.getWriter().write("0");
        }
        return null;
    }
    public void activate(HttpServletRequest request,HttpServletResponse response){
        response.setContentType("text/html;charset=utf-8");
        String base64Email = request.getParameter("e");
        String base64Code = request.getParameter("c");
        byte[] byteEmail = Base64.getDecoder().decode(base64Email);
        byte[] byteCode = Base64.getDecoder().decode(base64Code);

        String email = new String(byteEmail);
        String Code = new String(byteCode);
        User user = userService.findByEmail(email);

        if (user.getCode().equals(Code)){
            userService.activate(user.getId());
            System.out.println("激活成功");
            try {
                response.getWriter().write("激活成功，<a href='http://localhost:8080/xiaomishop_vainglory/login.jsp'>去登录</a>");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else {
            System.out.println("激活失败");
        }
    }

}
