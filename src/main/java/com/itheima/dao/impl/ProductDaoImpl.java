package com.itheima.dao.impl;

import com.itheima.dao.ProductDao;
import com.itheima.domain.Product;
import com.itheima.utils.C3P0Utils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.io.IOException;
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
}
