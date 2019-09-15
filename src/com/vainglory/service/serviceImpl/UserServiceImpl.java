package com.vainglory.service.serviceImpl;


import com.vainglory.dao.IUserDao;
import com.vainglory.dao.daoImpl.UserDaoImpl;
import com.vainglory.domain.Address;
import com.vainglory.domain.User;
import com.vainglory.service.IUserService;
import com.vainglory.utils.CodeUtils;
import com.vainglory.utils.EmailUtils;
import com.vainglory.utils.MD5Util;

import java.util.List;

public class UserServiceImpl implements IUserService {

    IUserDao userDao = new UserDaoImpl();

    @Override
    public void register(User user) {
        //设置初始状态
        user.setFlag(0);
        user.setRole(1);
        user.setCode(CodeUtils.getCode());
        user.setPassword(MD5Util.encode(user.getPassword()));
        userDao.add(user);
        EmailUtils.sendEmail(user);
    }

    @Override
    public boolean delete(Integer id) {
        return userDao.delete(id);
    }

    @Override
    public String update(User user) {
        System.out.println(user);
        if(user.getUsername()==null||user.getUsername().trim().length()==0){
            return  "用户名不能为空。";
        }
        if(user.getEmail()==null||user.getEmail().trim().length()==0){
            return "邮箱不能为空。";
        }
        int result = userDao.update(user);
        if(result==1){
            return  "修改成功。";
        }else {
            return "服务器内部错误，请稍后再试。";
        }
    }

    @Override
    public String updatePwd(Integer id, String oldPwd, String newPwd) {

        if (oldPwd==null||oldPwd.trim().length()==0){
            return "旧密码不能为空";
        }
        if(newPwd==null||newPwd.trim().length()==0){
            return "新密码不能为空";
        }
        User user = userDao.findById(id);
        String MD5Pwd = MD5Util.encode(oldPwd);
        if (!MD5Pwd.equals(user.getPassword())){
            return "旧密码不正确";
        }else{
            user.setPassword(MD5Pwd);
            int result = userDao.update(user);
            if (result==1){
                System.out.println("修改完成，2秒后跳转登录页面。");
                return "修改完成，2秒后跳转登录页面。";
            }else {
                return "服务器错误，请重试";
            }
        }
    }

    @Override
    public List<User> queryAll(Integer flag) {
        return userDao.findAll(flag);
    }

    @Override
    public User findById(Integer id) {
        return userDao.findById(id);
    }

    @Override
    public User checkUserName(String username) {
        return userDao.findByUserName(username);
    }

    @Override
    public User findByEmail(String email) {
        return userDao.findByEmail(email);
    }

    @Override
    public void activate(Integer uid) {
        userDao.activate(uid);
    }
}
