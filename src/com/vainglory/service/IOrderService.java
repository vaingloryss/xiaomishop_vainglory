package com.vainglory.service;

import com.vainglory.domain.Address;
import com.vainglory.domain.Cart;
import com.vainglory.domain.Order;
import com.vainglory.domain.OrderDetail;

import java.util.List;

public interface IOrderService {
     List<Cart> getCarts(Integer uid);
     List<Address> getAddresses(Integer uid);
    void addOrder(Order order, List<OrderDetail> orderDetails);
    void updateOrderStatus(String oid,String status);
    List<Order> findOrderByUid(Integer id);
    Order findOrderByOid(String oid);
}
