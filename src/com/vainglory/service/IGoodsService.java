package com.vainglory.service;

import com.vainglory.domain.Goods;
import com.vainglory.domain.GoodsType;
import com.vainglory.domain.PageBean;

import java.util.List;

public interface IGoodsService {
    public List<GoodsType> getGoodsTypeList();
    public List<GoodsType> getGoodsTypeAjax();
    public List<Goods> getGoodsListByTypeId(Integer typeid);
    PageBean<Goods> findPageByWhere(int pageNum, int pageSize, String condition);
    Goods findById(int gid);
}
