package com.vainglory.service;

import com.vainglory.domain.Goods;
import com.vainglory.domain.GoodsType;
import com.vainglory.domain.PageBean;

import java.util.List;

public interface IGoodsService {
    List<GoodsType> getGoodsTypeList();
    List<GoodsType> getGoodsTypeAjax();
    PageBean<Goods> findPageByWhere(int pageNum, int pageSize, String condition);
    Goods findById(int gid);
}
