package com.itheima.service;

import com.itheima.domain.OrderItem;
import com.itheima.domain.Orders;

import java.sql.SQLException;
import java.util.List;

public interface OrdersService {
    //添加订单
    void addOrders(Orders orders, List<OrderItem> orderItem);

    //查询订单列表
    List<Orders> ordersListWithPage(String uid,int pageNumber,int pageSize);

    //查询订单总数
    long ordersCount(String uid);
}
