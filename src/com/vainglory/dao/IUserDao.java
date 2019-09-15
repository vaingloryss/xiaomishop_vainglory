package com.vainglory.dao;

import com.vainglory.domain.Address;
import com.vainglory.domain.User;

import java.util.List;

public interface IUserDao {
    int add(User user);
    User findById(Integer id);
    boolean delete(Integer id);
    int update(User user);
    List<User> findAll(Integer flag);
    User findByUserName(String userName);


}
