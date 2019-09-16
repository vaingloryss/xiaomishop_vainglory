package com.vainglory.web.servlet;

import com.google.gson.Gson;
import com.vainglory.domain.Goods;
import com.vainglory.domain.GoodsType;
import com.vainglory.domain.PageBean;
import com.vainglory.service.IGoodsService;
import com.vainglory.service.serviceImpl.GoodsServiceImpl;
import com.vainglory.utils.StringUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "GoodsServlet",value = "/goodsServlet")
public class GoodsServlet extends BaseServlet {
    private IGoodsService goodsService = new GoodsServiceImpl();

    public void goodsType(HttpServletRequest request,HttpServletResponse response){
        response.setContentType("application/json;charset=utf-8");
        List<GoodsType> goodsTypeList = goodsService.getGoodsTypeAjax();
        Gson gson = new Gson();
        String strGoodsTypeLists = gson.toJson(goodsTypeList);
        try {
            response.getWriter().println(strGoodsTypeLists);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /*public String getGoodsListByTypeId(HttpServletRequest request,HttpServletResponse response) throws IOException {
        System.out.println("getGoodsListByTypeId");
        String typeId = request.getParameter("typeId");
        System.out.println(typeId);
        List<Goods> goodsList = goodsService.getGoodsListByTypeId(Integer.parseInt(typeId));
        if(goodsList!=null){
            request.getSession().setAttribute("goodsList", goodsList);
            return "goodsList.jsp";
        }else{
            System.out.println("没找到商品");
        }
        return null;
    }*/

    public String getGoodsListByTypeId(HttpServletRequest request, HttpServletResponse response) throws Exception{
        String typeId = request.getParameter("typeId");
        String _pageNum = request.getParameter("pageNum");
        String _pageSize=request.getParameter("pageSize");
        int pageNum=1;
        int pageSize=8;
        if(!StringUtils.isEmpty(_pageNum)){
            pageNum=Integer.parseInt(_pageNum);
            if(pageNum<1){
                pageNum=1;
            }
        }
        if(!StringUtils.isEmpty(_pageSize)){
            pageSize=Integer.parseInt(_pageSize);
            if(pageSize<1){
                pageSize=8;
            }
        }
        System.out.println(pageNum+"..."+pageSize);
        IGoodsService goodsService=new GoodsServiceImpl();
        String condition="";
        if(typeId!=null&&typeId.trim().length()!=0) {
            condition = "typeid=" + typeId;
        }
        try {
            PageBean<Goods> pageBean=goodsService.findPageByWhere(pageNum,pageSize,condition);  // typeId=1;
            request.setAttribute("pageBean", pageBean);
            request.setAttribute("typeId", typeId);
            return "/goodsList.jsp";
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/index.jsp";
        }
    }

    public String goodsDetail(HttpServletRequest request,HttpServletResponse response){
        String gid = request.getParameter("id");
        Goods goods = goodsService.findById(Integer.parseInt(gid));
        request.setAttribute("goods",goods);
        return "/goodsDetail.jsp";
    }
}
