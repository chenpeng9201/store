package com.itheima.dao;

import com.itheima.domain.OrderItem;
import com.itheima.domain.Orders;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface OrdersDao {
    //添加订单方法,传递Orders对象
    void submitOrder(Connection con, Orders orders)throws SQLException;
    //添加订单项方法,传递OrderItem对象
    void submitOrderItem(Connection con, OrderItem orderItem) throws SQLException;
    //查询订单列表
    List<Orders> ordersListWithPage(String uid, int pageNumber,int pageSize) throws SQLException;
    //查询订单总数量
    long ordersCount(String uid) throws SQLException;

}
