package com.vainglory.service.serviceImpl;

import com.vainglory.dao.IGoodsDao;
import com.vainglory.dao.daoImpl.DaoFactory;
import com.vainglory.domain.Goods;
import com.vainglory.domain.GoodsType;
import com.vainglory.domain.PageBean;
import com.vainglory.service.IGoodsService;

import java.util.List;

public class GoodsServiceImpl implements IGoodsService {

    private IGoodsDao goodsDao = DaoFactory.getGoodsDao();

    @Override
    public List<GoodsType> getGoodsTypeList() {
        return goodsDao.getGoodsTypeList();
    }

    @Override
    public List<GoodsType> getGoodsTypeAjax() {
        return goodsDao.getGoodsTypeAjax();
    }

    @Override
    public PageBean<Goods> findPageByWhere(int pageNum, int pageSize, String condition) {

        long totalSize=goodsDao.getCount(condition);
        List<Goods> data= goodsDao.findPageByWhere(pageNum,pageSize,condition);

        return new PageBean<>(pageNum, pageSize, totalSize , data);
    }

    @Override
    public Goods findById(int gid) {
        Goods goods = goodsDao.findById(gid); //goodsType null
        System.out.println(goods.toString());
        //根据商品的类型id，查询商品类型
        GoodsType goodsType = goodsDao.findTypeById(goods.getTypeid());
        goods.setGoodsType(goodsType);
        return goods;
    }
}
