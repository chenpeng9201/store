package com.itheima.web;

import com.itheima.domain.*;
import com.itheima.service.OrdersService;
import com.itheima.utils.BeanFactory;
import com.itheima.utils.UUIDUtils;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@WebServlet(urlPatterns = "/orders")
public class OrdersServlet extends BaseServlet {
    OrdersService ordersService = BeanFactory.newInstance(OrdersService.class);
    protected void addOrders(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //添加订单
        //从session域购物车中获取订单所需数据
        /*
        * 1.判断用户是否登录，没有登录则直接返回
        * 2.判断购物车是否为空
        * 3.添加购物车
        * */
        User user = (User)request.getSession().getAttribute("user");
        if(user==null){
            Result rs = new Result(Result.NOLOGIN,"尚未登录，请登录！");
            response.getWriter().print(JSONObject.fromObject(rs));
            return;
        }
        Cart cart = (Cart)request.getSession().getAttribute("cart");
        if(cart.getCartItem().size()==0){
            //购物车为空，给提示
            Result rs = new Result(Result.FAILS,"购物车中没有商品!");
            response.getWriter().print(JSONObject.fromObject(rs));
            return;
        }

        //将购物车数据转换成订单所需数据
        String oid = UUIDUtils.getUUID();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String orderTime = sdf.format(new Date());
        //封装主订单数据
        Orders orders = new Orders();
        orders.setOid(oid);
        orders.setOrdertime(orderTime);
        orders.setState(Constr.ORDER_WEIFUKUAN);
        orders.setUid(user.getUid());
        orders.setTotal(cart.getTotalprice());

        //封装orderItem
        List<OrderItem> orderItemList = new ArrayList<>();
        Collection<CartItem> cartItems = cart.getCartItem();
        for (CartItem item : cartItems) {
            //创建订单项对象
            OrderItem orderItem = new OrderItem();
            orderItem.setCount(item.getCount());
            orderItem.setOid(oid);
            orderItem.setPid(item.getProduct().getPid());
            orderItem.setSubTotal(item.getSubtotal());
            orderItemList.add(orderItem);
        }

        //调用业务层方法添加订单
        ordersService.addOrders(orders,orderItemList);
        Result rs = new Result(Result.SUCCESS,"添加订单成功");
        response.getWriter().print(JSONObject.fromObject(rs));
    }
}
