package com.itheima.service;

import com.itheima.domain.OrderItem;
import com.itheima.domain.Orders;

import java.util.List;

public interface OrdersService {
    //添加订单
    void addOrders(Orders orders, List<OrderItem> orderItem);
}
