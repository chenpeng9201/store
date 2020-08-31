package com.itheima.dao;

import com.itheima.domain.OrderItem;
import com.itheima.domain.Orders;

import java.sql.Connection;
import java.sql.SQLException;

public interface OrdersDao {
    //添加订单方法,传递Orders对象
    void submitOrder(Connection con, Orders orders)throws SQLException;
    //添加订单项方法,传递OrderItem对象
    void submitOrderItem(Connection con, OrderItem orderItem) throws SQLException;
}
