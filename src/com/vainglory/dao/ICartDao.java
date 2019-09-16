package com.vainglory.dao;

import com.vainglory.domain.Cart;

import java.util.List;

public interface ICartDao {
    int add(Cart cart);
    int deleteByUid(Integer uid);
    Cart findByUidAndGid(Integer uid, Integer gid);
    int update(Cart cart);
    List<Cart> finByUid(Integer uid);
    int deleteByUidAndGid(Integer uid, Integer gid);
}
