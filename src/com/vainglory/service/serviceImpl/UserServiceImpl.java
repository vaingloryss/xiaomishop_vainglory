package com.vainglory.service.serviceImpl;


import com.vainglory.dao.IUserDao;
import com.vainglory.dao.daoImpl.UserDaoImpl;
import com.vainglory.domain.User;
import com.vainglory.service.IUserService;
import com.vainglory.utils.CodeUtils;
import com.vainglory.utils.EmailUtils;
import com.vainglory.utils.MD5Util;

import java.util.List;

public class UserServiceImpl implements IUserService {

    private IUserDao userDao = new UserDaoImpl();

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
