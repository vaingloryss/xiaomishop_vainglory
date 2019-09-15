package com.vainglory.admin;

import com.alibaba.fastjson.JSON;
import com.vainglory.domain.GoodsType;
import com.vainglory.domain.User;
import com.vainglory.service.IGoodsService;
import com.vainglory.service.IUserService;
import com.vainglory.service.serviceImpl.GoodsServiceImpl;
import com.vainglory.service.serviceImpl.UserServiceImpl;
import com.vainglory.web.servlet.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "GoodsManageServlet",value = "/goodsManage")
public class GoodsManageServlet extends BaseServlet {

    IGoodsService goodsService = new GoodsServiceImpl();
    public String showAllGoodsType(HttpServletRequest request,HttpServletResponse response){
        List<GoodsType> goodsTypeList = goodsService.getGoodsTypeList();
        request.getSession().setAttribute("goodsTypeList",goodsTypeList);
        System.out.println("showAllGoodsType:"+goodsTypeList.size());
        return "/admin/showGoodsType.jsp";
    }
}
