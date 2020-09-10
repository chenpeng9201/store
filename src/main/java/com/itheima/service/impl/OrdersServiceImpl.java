package com.itheima.service.impl;

import com.itheima.dao.OrdersDao;
import com.itheima.domain.OrderItem;
import com.itheima.domain.Orders;
import com.itheima.service.OrdersService;
import com.itheima.utils.BeanFactory;
import com.itheima.utils.ConnectionManager;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrdersServiceImpl implements OrdersService {
    OrdersDao ordersDao = BeanFactory.newInstance(OrdersDao.class);

    //分页查询订单列表
    @Override
    public List<Orders> ordersListWithPage(String uid,int pageNumber,int pageSize) {
        List<Orders> ordersList = new ArrayList<>();
        try {
            ordersList = ordersDao.ordersListWithPage(uid, pageNumber, pageSize);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ordersList;
    }

    //查询订单总数量
    @Override
    public long ordersCount(String uid){
        long count = 0;
        try {
            count = ordersDao.ordersCount(uid);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    //添加订单ThreadLocal版本
    public void addOrders(Orders orders, List<OrderItem> orderItem) {
        Connection con = null;
        try{
            con = ConnectionManager.getConnection();
            ConnectionManager.begin();
            ordersDao.submitOrder(con,orders);
            for (OrderItem item : orderItem) {
                ordersDao.submitOrderItem(con,item);
            }
            //提交事务
            ConnectionManager.commit();
        }
        catch (Exception e){
            try {
                ConnectionManager.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        }finally {
            ConnectionManager.close();
        }
    }




    //添加订单
    /*@Override
    public void addOrders(Orders orders, List<OrderItem> orderItem) {
        Connection con = null;
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/store331?serverTimezone=UTC","root","root");
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
    }*/
}
