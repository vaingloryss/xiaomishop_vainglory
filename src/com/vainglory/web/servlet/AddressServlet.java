package com.vainglory.web.servlet;

import com.vainglory.domain.Address;
import com.vainglory.domain.User;
import com.vainglory.service.IAddressService;
import com.vainglory.service.serviceImpl.AddressServiceImpl;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

@WebServlet(name = "AddressServlet" ,value = "/addressServlet")
public class AddressServlet extends BaseServlet {
    private IAddressService addressService = new AddressServiceImpl();

    public String show(HttpServletRequest request,HttpServletResponse response){
        User user = (User) request.getSession().getAttribute("user");
        List<Address> addresses =  addressService.getAddressList(user.getId());
        if(addresses!=null){
            request.getSession().setAttribute("addresses",addresses);
        }
        return "redirect:/self_info.jsp";
    }
    public String addAddress(HttpServletRequest request,HttpServletResponse response){
        Address address = new Address();
        Map<String, String[]> parameterMap = request.getParameterMap();
        boolean b=false;
        try {
            BeanUtils.populate(address,parameterMap);
            b = addressService.addAddress(address);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (b) return show(request, response);
        System.out.println("添加地址失败。");
        return null;
    }
    public String deleteAddress(HttpServletRequest request,HttpServletResponse response){
        String id = request.getParameter("id");
        boolean b = addressService.deleteAddress(Integer.parseInt(id));
        if (b){
            return show(request,response);
        }
        System.out.println("删除地址失败。");
        return null;
    }
    public String defaultAddress(HttpServletRequest request, HttpServletResponse response){
        String addressId = request.getParameter("id");
        User user = (User) request.getSession().getAttribute("user");
        boolean b = addressService.setDefault(Integer.parseInt(addressId), user.getId());
        if (b){
            return show(request,response);
        }
        System.out.println("设置默认地址失败。");
        return null;
    }
    public String updateAddress(HttpServletRequest request,HttpServletResponse response){
        Address address = new Address();
        try {
            BeanUtils.populate(address,request.getParameterMap());
        } catch (Exception e) {
            e.printStackTrace();
        }
        boolean b = addressService.updateAddress(address);
        if (b){
            return show(request,response);
        }
        return null;
    }

}
