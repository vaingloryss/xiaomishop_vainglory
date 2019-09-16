package com.vainglory.service;

import com.vainglory.domain.User;

import java.util.List;

public interface IUserService {
    void register(User user);
    boolean delete(Integer id);
    String update(User user);
    List<User> queryAll(Integer flag);
    User findById(Integer id);
    User checkUserName(String username);
    User findByEmail(String email);
    void activate(Integer uid);
}
