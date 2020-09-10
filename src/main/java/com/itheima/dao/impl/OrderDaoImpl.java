package com.itheima.dao.impl;

import com.itheima.dao.OrdersDao;
import com.itheima.domain.OrderItem;
import com.itheima.domain.OrderItemView;
import com.itheima.domain.Orders;
import com.itheima.utils.C3P0Utils;
import com.itheima.utils.UUIDUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class OrderDaoImpl implements OrdersDao {
    private QueryRunner qr = new QueryRunner(C3P0Utils.getDataSource());
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
        String sql = "insert into orderitem(itemid,count,subtotal,pid,oid) values(?,?,?,?,?)";
        Object[] param = {UUIDUtils.getUUID(),orderItem.getCount(),orderItem.getSubTotal(),orderItem.getPid(),orderItem.getOid()};
        qr.update(con,sql,param);
    }

    //查询订单列表
    @Override
    public List<Orders> ordersListWithPage(String uid, int pageNumber,int pageSize) throws SQLException {
        String sql = "";
        sql = "select * from orders where uid = ? limit ?,?";
        //获取订单列表
        List<Orders> ordersList = qr.query(sql, new BeanListHandler<Orders>(Orders.class), uid,(pageNumber-1)*pageSize,pageSize);
        //循环每个订单，获取订单项
        for (Orders orders : ordersList) {
            sql = "select t2.pid,t2.count,t2.subtotal,t3.pname,t3.pimage,t3.shop_price\n" +
                    "  from orders t1,orderitem t2,product t3\n" +
                    " where t1.oid = t2.oid\n" +
                    "   and t2.pid = t3.pid\n" +
                    "   and t1.oid = ?";
            List<OrderItemView> itemViewList = qr.query(sql, new BeanListHandler<OrderItemView>(OrderItemView.class), orders.getOid());
            //将订单项塞进订单中
            orders.setOrderItemList(itemViewList);
        }
        return ordersList;
    }

    //查询订单总数
    @Override
    public long ordersCount(String uid) throws SQLException {
        String sql = "select count(*) from orders where uid = ?";
        Long count = qr.query(sql, new ScalarHandler<Long>(), uid);
        return count;
    }


}
