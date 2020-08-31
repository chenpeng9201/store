package com.itheima.dao.impl;

import com.itheima.dao.OrdersDao;
import com.itheima.domain.OrderItem;
import com.itheima.domain.Orders;
import com.itheima.utils.UUIDUtils;
import org.apache.commons.dbutils.QueryRunner;

import java.sql.Connection;
import java.sql.SQLException;

public class OrderDaoImpl implements OrdersDao {
    private QueryRunner qr = new QueryRunner();
    //提交订单
    @Override
    public void submitOrder(Connection con, Orders orders) throws SQLException {
        String sql = "insert into orders(oid,ordertime,total,state,uid) values(?,?,?,?,?)";
        Object[] param = {orders.getOid(),orders.getOrdertime(),orders.getTotal(),orders.getState(),orders.getUid()};
        qr.update(con,sql,param);
    }

    //提交订单项
    @Override
    public void submitOrderItem(Connection con, OrderItem orderItem) throws SQLException {
        String sql = "insert into orderitem(itemid,count,subtotal,pid,oid) values(?,?,?,?)";
        Object[] param = {UUIDUtils.getUUID(),orderItem.getCount(),orderItem.getSubTotal(),orderItem.getPid(),orderItem.getOid()};
        qr.update(con,sql,param);
    }
}
