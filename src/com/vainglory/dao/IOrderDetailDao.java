package com.vainglory.dao;

import com.vainglory.domain.OrderDetail;

import java.util.List;

public interface IOrderDetailDao {
    void add(OrderDetail orderDetail);
    List<OrderDetail> findByOid(String id);
}
