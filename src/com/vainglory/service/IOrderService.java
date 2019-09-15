package com.vainglory.service;

import com.vainglory.domain.Address;
import com.vainglory.domain.Cart;
import com.vainglory.domain.Order;
import com.vainglory.domain.OrderDetail;

import java.util.List;

public interface IOrderService {
     List<Cart> getCarts(Integer uid);
     List<Address> getAddresses(Integer uid);
    int addOrder(Order order, List<OrderDetail> orderDetails);
    void updateOrderStatus(String oid,String status);
    List<Order> findOrderByUid(Integer id);

    /*OrderDetail getOrderDetail(String oid);*/

    Order findOrderByOid(String oid);
}
