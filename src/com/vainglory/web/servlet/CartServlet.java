package com.vainglory.web.servlet;

import com.vainglory.domain.Cart;
import com.vainglory.domain.Goods;
import com.vainglory.domain.User;
import com.vainglory.service.ICartService;
import com.vainglory.service.IGoodsService;
import com.vainglory.service.serviceImpl.CartServiceImpl;
import com.vainglory.service.serviceImpl.GoodsServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

@WebServlet(name = "CartServlet",value = "/cartServlet")
public class CartServlet extends BaseServlet {

    ICartService cartService = new CartServiceImpl();
    IGoodsService goodsService = new GoodsServiceImpl();

    public String addGoods(HttpServletRequest request,HttpServletResponse response){
        User user = (User) request.getSession().getAttribute("user");
        //判断有没有登录
        if(user==null){
            return "redirect:/login.jsp";
        }
        //获取参数
        Integer gid = Integer.parseInt(request.getParameter("goodsId"));
        Integer number = Integer.parseInt(request.getParameter("number"));

        if (gid==null){
            return "redirect:/index.jsp";
        }
        //购物车
        Cart oneCart = cartService.findByUidAndGid(user.getId(),gid);
        Goods goods = goodsService.findById(gid);
        if (oneCart==null){
            //添加
            Cart cart = new Cart(user.getId(),gid,number,goods.getPrice().multiply(new BigDecimal(number)));
            boolean b = cartService.addCart(cart);
            if (b){
                System.out.println("添加成功");
                return "redirect:/cartSuccess.jsp";
            }
        }else {
            if(number==0 ){
                boolean b = cartService.deleteCart(user.getId(),gid);
                if(b){
                    System.out.println("删除成功");
                    return "redirect:/cartSuccess.jsp";
                }
            }
            //更新
            oneCart.setNum(number+oneCart.getNum());
            oneCart.setMoney(goods.getPrice().multiply(new BigDecimal(oneCart.getNum())));
            boolean b = cartService.updateCart(oneCart);
            if (b){
                System.out.println("添加成功");
                return "redirect:/cartSuccess.jsp";
            }
        }
        System.out.println("添加购物车失败");
        return null;
    }

    public String showCart(HttpServletRequest request,HttpServletResponse response){
        User user = (User) request.getSession().getAttribute("user");
        //判断有没有登录
        if(user==null){
            return "redirect:/login.jsp";
        }
        List<Cart> carts = cartService.finByUid(user.getId());
        //List<Cart> carts = cartService.findAll();
        request.setAttribute("carts",carts);
        return "/cart.jsp";
    }

    public String clearCart(HttpServletRequest request,HttpServletResponse httpServletResponse){

        User user = (User) request.getSession().getAttribute("user");
        if (user==null){
            return "redirect:/login.jsp";
        }
        boolean b = cartService.clearCart(user.getId());
        if (b){
            System.out.println("删除成功");
            return "redirect:/cartSuccess.jsp";
        }
        System.out.println("删除失败");
        return "redirect:/cartSuccess.jsp";
    }

}
