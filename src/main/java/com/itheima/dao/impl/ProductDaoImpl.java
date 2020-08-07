package com.itheima.dao.impl;

import com.itheima.dao.ProductDao;
import com.itheima.domain.PageBean;
import com.itheima.domain.Product;
import com.itheima.utils.C3P0Utils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.List;

public class ProductDaoImpl implements ProductDao {
    QueryRunner qr = new QueryRunner(C3P0Utils.getDataSource());
    @Override
    public List<Product> findNew() throws SQLException {
        String sql = "select * from product order by pdate desc limit 9";
        return qr.query(sql,new BeanListHandler<Product>(Product.class));
    }

    @Override
    public List<Product> findIsHot() throws SQLException {
        String sql = "select * from product where is_hot = 1 limit 9";
        return qr.query(sql,new BeanListHandler<Product>(Product.class));
    }

    //展示商品详情
    @Override
    public Product pinfo(String pid) throws SQLException {
        String sql = "select * from product where pid = ?";
        Object[] param = {pid};
        return qr.query(sql,new BeanHandler<Product>(Product.class),param);
    }


    //分页查询每个分类下的商品
    @Override
    public List<Product> productList(int currentPage, int pageSize, String cid) throws SQLException{
        String sql = "select * from product where cid = ? limit ?,?";
        return qr.query(sql,new BeanListHandler<Product>(Product.class),cid,(currentPage-1)*pageSize,pageSize);
    }


    //查询分类下的总页数
    @Override
    public Long totalCount(String cid) throws SQLException {
        String sql = "select count(1) from product where cid = ?";
        Long query = qr.query(sql, new ScalarHandler<Long>(), cid);
        return query;
    }


}
