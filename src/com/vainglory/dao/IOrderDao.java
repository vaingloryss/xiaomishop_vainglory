package com.vainglory.dao;

import com.vainglory.domain.Order;

import java.util.List;

public interface IOrderDao {
    void add(Order order);
    void updateOrderStatus(String oid, String status);
    List<Order> findByUid(Integer id);
    Order findByOid(String oid);

}
