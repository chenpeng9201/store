package com.itheima.web;

import com.itheima.domain.Cart;
import com.itheima.domain.CartItem;
import com.itheima.domain.Product;
import com.itheima.service.ProductService;
import com.itheima.utils.BeanFactory;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/Cart")
public class CartServlet extends BaseServlet {
    //Bean工厂获取业务层实现类
    ProductService ps = BeanFactory.newInstance(ProductService.class);
    protected void addCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //添加购物车
        String pid = request.getParameter("pid");
        int count = Integer.parseInt(request.getParameter("count"));
        Product pinfo = ps.pinfo(pid);
        CartItem cartItem = new CartItem();
        cartItem.setProduct(pinfo);
        cartItem.setCount(count);
        cartItem.setSubtotal(count*pinfo.getShop_price());
        //调用购物车方法添加购物车，需要从session中获得购物车，不能直接new
        Cart cart = getCart(request);
        cart.addCart(cartItem);
        Result rs = new Result(Result.SUCCESS,"添加成功",cart);
        response.getWriter().print(JSONObject.fromObject(rs));
    }

    private Cart getCart(HttpServletRequest request){
        Cart cart = (Cart)request.getSession().getAttribute("cart");
        if(cart==null){
            cart = new Cart();
            //放入session中
            request.getSession().setAttribute("cart",cart);
        }
        return cart;
    }
}