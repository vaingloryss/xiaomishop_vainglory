package com.vainglory.dao;

import com.vainglory.domain.Goods;
import com.vainglory.domain.GoodsType;

import java.util.List;

public interface IGoodsDao {
    //1.获取商品分类列表
    List<GoodsType> getGoodsTypeList();
    List<GoodsType> getGoodsTypeAjax();
    List<Goods> getGoodsListByTypeId(Integer typeid);
    long getCount(String condition);
    List<Goods> findPageByWhere(int pageNum,int pageSize,String condition);
    Goods findById(int gid);
    GoodsType findTypeById(Integer id);
}
