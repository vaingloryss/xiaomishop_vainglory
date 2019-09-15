package com.vainglory.service.serviceImpl;

import com.vainglory.dao.IGoodsDao;
import com.vainglory.dao.daoImpl.GoodsDaoImpl;
import com.vainglory.domain.Goods;
import com.vainglory.domain.GoodsType;
import com.vainglory.domain.PageBean;
import com.vainglory.service.IGoodsService;

import java.util.List;

public class GoodsServiceImpl implements IGoodsService {

    IGoodsDao goodsDao = new GoodsDaoImpl();

    @Override
    public List<GoodsType> getGoodsTypeList() {
        return goodsDao.getGoodsTypeList();
    }

    @Override
    public List<GoodsType> getGoodsTypeAjax() {
        return goodsDao.getGoodsTypeAjax();
    }

    @Override
    public List<Goods> getGoodsListByTypeId(Integer typeid) {
        return goodsDao.getGoodsListByTypeId(typeid);
    }
    @Override
    public PageBean<Goods> findPageByWhere(int pageNum, int pageSize, String condition) {

        long totalSize=goodsDao.getCount(condition);
        List<Goods> data= goodsDao.findPageByWhere(pageNum,pageSize,condition);

        PageBean<Goods> pageBean=new PageBean<>(pageNum, pageSize, totalSize , data);

        return pageBean;
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
