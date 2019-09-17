package com.vainglory.dao.daoImpl;

import com.vainglory.dao.*;

public class DaoFactory {
    //地址DAO
    public static IAddressDao getAddressDao(){
        return new AddressDaoImpl();
    }
    //购物车DAO
    public static ICartDao getCartDao(){
        return new CartDaoImpl();
    }
    //商品DAO
    public static IGoodsDao getGoodsDao(){
        return new GoodsDaoImpl();
    }
    //订单DAO
    public static IOrderDao getOrderDao(){
        return new OrderDaoImpl();
    }
    //订单详情DAO
    public static IOrderDetailDao getOrderDetailDao(){
        return new OrderDetailDaoImpl();
    }
    //用户DAO
    public static IUserDao getUserDao(){
        return new UserDaoImpl();
    }
}
