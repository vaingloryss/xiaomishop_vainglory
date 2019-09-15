package com.vainglory.service.serviceImpl;

import com.vainglory.dao.ICartDao;
import com.vainglory.dao.daoImpl.CartDaoImpl;
import com.vainglory.domain.Cart;
import com.vainglory.domain.Goods;
import com.vainglory.service.ICartService;
import com.vainglory.service.IGoodsService;

import java.awt.geom.FlatteningPathIterator;
import java.util.List;

public class CartServiceImpl implements ICartService {

    ICartDao cartDao = new CartDaoImpl();
    IGoodsService goodsService = new GoodsServiceImpl();

    @Override
    public Cart findByUidAndGid(Integer uid, Integer gid) {
        return cartDao.findByUidAndGid(uid,gid);
    }

    @Override
    public boolean updateCart(Cart cart) {
        int result = cartDao.update(cart);
        if (result==1){
            return true;
        }
        return false;
    }

    @Override
    public boolean addCart(Cart cart) {
        int result = cartDao.add(cart);
        if (result==1){
            return true;
        }
        return false;
    }

    @Override
    public List<Cart> finByUid(Integer uid) {
        List<Cart> carts = cartDao.finByUid(uid);
        if (carts !=null){
            for (Cart cart : carts) {
                Goods goods = goodsService.findById(cart.getGid());
                cart.setGoods(goods);
            }
        }
        return carts;
    }

    @Override
    public List<Cart> findAll() {
        return cartDao.findAll();
    }

    @Override
    public boolean deleteCart(Integer uid, Integer gid) {
        int result = cartDao.deleteByUidAndGid(uid,gid);
        return false;
    }

    @Override
    public boolean clearCart(Integer uid) {
        int result = cartDao.deleteByUid(uid);
        return true;
    }
}
