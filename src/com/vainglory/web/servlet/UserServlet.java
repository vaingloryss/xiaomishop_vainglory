package com.vainglory.web.servlet;

import com.vainglory.domain.Address;
import com.vainglory.domain.User;
import com.vainglory.service.IUserService;
import com.vainglory.service.serviceImpl.UserServiceImpl;
import com.vainglory.utils.MD5Util;
import com.vainglory.utils.StringUtils;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

@WebServlet(name = "UserServlet",value = {"/userServlet"})
public class UserServlet extends BaseServlet {
    IUserService userService = new UserServiceImpl();
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

/*    protected void doPost(HttpServletRequest request, HttpServletResponse response){
        try {
            //设置编码的代码不能放在获取输出流的代码之后，否则设置编码无效
            request.setCharacterEncoding("utf-8");
            response.setContentType("text/html;charset=utf-8");
            //try-with-resources语句
            try(PrintWriter out = response.getWriter()){
                //1：注册，2：删除，3：修改信息，4：修改密码 , 5：获取所有
                String option = request.getParameter("op");
                if("1".equals(option)){
                    out.write(register(request));
                }
                if ("2".equals(option)){
                    Integer id = Integer.parseInt(request.getParameter("id"));
                    boolean result = delete(id);
                    if (result){
                        response.sendRedirect("userServlet?op=5");
                    }else {
                        out.write("删除失败。");
                    }
                }
                if("3".equals(option)){
                    String resultInfo = update(request);
                    if (resultInfo.equals("修改成功。")){
                        response.sendRedirect("userServlet?op=5");
                    }
                    out.write(resultInfo);
                }
                if("4".equals(option)){
                    out.write(updatePwd(request));
                }
                if ("5".equals(option)){
                    List<User> userList = userList();
                    request.setAttribute("userList",userList);
                    request.getRequestDispatcher("userList.jsp").forward(request,response);
                }
                if("6".equals(option)){
                    String id = request.getParameter("id");
                    User user = userService.findById(Integer.parseInt(id));
                    request.setAttribute("user",user);
                    request.getRequestDispatcher("updateInfo.jsp").forward(request,response);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response){
        doPost(request,response);
    }*/



    /*private boolean delete(Integer id){
        return userService.delete(id);
    }
    private String update(HttpServletRequest request)throws Exception{
        User user = new User();
        BeanUtils.populate(user,request.getParameterMap());
        return userService.update(user);
    }
    private String updatePwd(HttpServletRequest request){
        return userService.updatePwd(Integer.parseInt(request.getParameter("id")),request.getParameter("oldPwd"),request.getParameter("newPwd"));
    }
    private List<User> userList(){
        return userService.queryAll();
    }*/

}
