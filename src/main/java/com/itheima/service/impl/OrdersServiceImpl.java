package com.itheima.service.impl;

import com.itheima.dao.OrdersDao;
import com.itheima.domain.OrderItem;
import com.itheima.domain.Orders;
import com.itheima.service.OrdersService;
import com.itheima.utils.BeanFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class OrdersServiceImpl implements OrdersService {
    OrdersDao ordersDao = BeanFactory.newInstance(OrdersDao.class);

    //添加订单
    @Override
    public void addOrders(Orders orders, List<OrderItem> orderItem) {
        Connection con = null;
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/store331","root","root");
            con.setAutoCommit(false);
            ordersDao.submitOrder(con,orders);
            for (OrderItem item : orderItem) {
                ordersDao.submitOrderItem(con,item);
            }
            //提交事务
            con.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                con.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }finally {
            if(con != null){
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
