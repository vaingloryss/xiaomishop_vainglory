package com.vainglory.web.servlet;

import cn.dsna.util.images.ValidateCode;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "PageValidateCode",value = "/code")
public class PageValidateCode extends BaseServlet {
    public void code(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ValidateCode validateCode = new ValidateCode(100,40,4,10);
        request.getSession().setAttribute("acode",validateCode.getCode());
        validateCode.write(response.getOutputStream());
    }

    public void checkCode(HttpServletRequest request,HttpServletResponse response) throws IOException {
        String code = request.getParameter("code");
        int result = 1;
        if (code.equalsIgnoreCase(request.getSession().getAttribute("acode").toString())){
            result = 0;
        }
        response.getWriter().println(result);
    }
}
