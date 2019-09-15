package com.vainglory.web.servlet;

import com.vainglory.domain.Order;
import com.vainglory.domain.OrderDetail;
import com.vainglory.service.IOrderService;
import com.vainglory.service.serviceImpl.OrderServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "PayServlet",value = "/payServlet")
public class PayServlet extends BaseServlet {
    //payServlet?op=toPayWeiXin&oid=20190914211337792&money=124505

    IOrderService orderService = new OrderServiceImpl();
    public String toPayWeiXin(HttpServletRequest request,HttpServletResponse response){
        System.out.println("toPayWeiXin");
        String orderId = request.getParameter("orderId");
        Order order = orderService.findOrderByOid(orderId);
        String goodsName = "";
        List<OrderDetail> orderDetails = order.getOrderDetails();
        for (OrderDetail orderDetail : orderDetails) {
            goodsName += orderDetail.getGoods().getName()+"，";
        }
        request.setAttribute("orderId",orderId);
        request.setAttribute("money",order.getMoney());
        request.setAttribute("goodsName",goodsName);

        return "/payWeixin.jsp";
    }
}
