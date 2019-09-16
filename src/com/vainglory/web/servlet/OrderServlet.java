package com.vainglory.web.servlet;

import com.vainglory.domain.*;
import com.vainglory.service.ICartService;
import com.vainglory.service.IOrderService;
import com.vainglory.service.serviceImpl.CartServiceImpl;
import com.vainglory.service.serviceImpl.OrderServiceImpl;
import com.vainglory.utils.RandomUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@WebServlet(name = "OrderServlet",value = "/orderServlet")
public class OrderServlet extends BaseServlet {

    private IOrderService orderService = new OrderServiceImpl();
    private ICartService cartService = new CartServiceImpl();

    public String getOrderView(HttpServletRequest request,HttpServletResponse response){
        User user = (User) request.getSession().getAttribute("user");
        if (user==null){
            return "redirect:/login.jsp";
        }
        List<Cart> carts = orderService.getCarts(user.getId());
        request.setAttribute("carts",carts);
        List<Address> addresses = orderService.getAddresses(user.getId());
        request.setAttribute("addresses",addresses);
        return "/order.jsp";
    }

    public String addOrder(HttpServletRequest request, HttpServletResponse response){
        User user = (User) request.getSession().getAttribute("user");
        if (user==null){
            return "redirect:/login.jsp";
        }
        int aid = Integer.parseInt(request.getParameter("aid"));
        List<Cart> carts = cartService.finByUid(user.getId());
        assert carts != null;
        if (carts==null&&carts.size()==0){
            request.setAttribute("msg", "购物车为空，请选择商品!");
            return "/message.jsp";
        }
        //生成订单ID
        String orderId = RandomUtils.createOrderId();

        //添加订单详情
        List<OrderDetail> orderDetails = new ArrayList<>();
        BigDecimal sum = new BigDecimal(0);
        for (Cart cart : carts) {
            OrderDetail orderDetail = new OrderDetail(null,orderId,cart.getGid(),cart.getNum(),cart.getMoney());
            orderDetails.add(orderDetail);
            sum = sum.add(cart.getMoney());
        }
        //创建订单
        Order order = new Order(orderId,user.getId(),sum,"1",new Date(),aid);

        orderService.addOrder(order,orderDetails);
        request.setAttribute("order",order);
        return "/orderSuccess.jsp";
    }

    public String orderList(HttpServletRequest request,HttpServletResponse response){
        User user = (User) request.getSession().getAttribute("user");
        if (user==null){
            return "redirect:/login.jsp";
        }
        List<Order> orders = orderService.findOrderByUid(user.getId());
        request.setAttribute("orders",orders);
        return "/orderList.jsp";
    }
    //getOrderDetail?oid=20190914112334333

/*    public String getOrderDetail(HttpServletRequest request,HttpServletResponse response){
        System.out.println("-----------------------getOrderDetail");
        String oid = request.getParameter("oid");
        OrderDetail orderDetail = orderService.getOrderDetail(oid);
        request.setAttribute("orderDetail",orderDetail);
        return "/orderDetail.jsp";
    }*/

    public String orderDetail(HttpServletRequest request,HttpServletResponse response){
        System.out.println("-----------------------orderDetail");
        String oid = request.getParameter("oid");

        Order order = orderService.findOrderByOid(oid);
        //System.out.println(order);
        request.setAttribute("order",order);
        return "/orderDetail.jsp";
    }

    public String confirmReceipt(HttpServletRequest request, HttpServletResponse response){
        //changeStatus?oid=20190914185910084
        String oid = request.getParameter("oid");
        orderService.updateOrderStatus(oid,"4");
        return orderList(request,response);
    }
}
