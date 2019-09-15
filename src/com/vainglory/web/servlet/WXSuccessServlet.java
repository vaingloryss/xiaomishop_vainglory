package com.vainglory.web.servlet;

import com.alibaba.fastjson.JSON;
import com.vainglory.service.IOrderService;
import com.vainglory.service.serviceImpl.OrderServiceImpl;
import com.vainglory.utils.WeiXinResult;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "WXSuccessServlet",value = "/wxSuccessServlet")
public class WXSuccessServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("-----------------wxSuccessServlet-------------------");

        String resultJson = request.getParameter("result");
        WeiXinResult weiXinResult = JSON.parseObject(resultJson,WeiXinResult.class);
        String result_code = weiXinResult.getResult().getResult_code();
        if (result_code.equals("SUCCESS")){
            if (weiXinResult.getType()==0){
                request.setAttribute("msg","您的订单号为:"+weiXinResult.getResult().getOut_trade_no()+",金额为:"+weiXinResult.getResult().getCash_fee()+"已经支付成功,等待发货~~");
            }else {
                response.getWriter().write("success");
            }
            IOrderService orderService = new OrderServiceImpl();
            orderService.updateOrderStatus(weiXinResult.getResult().getOut_trade_no(),"2");
        }else {
            System.out.println("支付失败");
            request.setAttribute("msg","您的订单号为:"+weiXinResult.getResult().getOut_trade_no()+"支付失败");
        }
        request.getRequestDispatcher("/message.jsp").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
