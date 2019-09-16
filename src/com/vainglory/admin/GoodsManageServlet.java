package com.vainglory.admin;

import com.vainglory.domain.GoodsType;
import com.vainglory.service.IGoodsService;
import com.vainglory.service.serviceImpl.GoodsServiceImpl;
import com.vainglory.web.servlet.BaseServlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@WebServlet(name = "GoodsManageServlet",value = "/goodsManage")
public class GoodsManageServlet extends BaseServlet {

    private IGoodsService goodsService = new GoodsServiceImpl();
    public String showAllGoodsType(HttpServletRequest request,HttpServletResponse response){
        List<GoodsType> goodsTypeList = goodsService.getGoodsTypeList();
        request.getSession().setAttribute("goodsTypeList",goodsTypeList);
        System.out.println("showAllGoodsType:"+goodsTypeList.size());
        return "/admin/showGoodsType.jsp";
    }
}
