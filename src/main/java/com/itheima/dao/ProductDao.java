package com.itheima.dao;

import com.itheima.domain.Product;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface ProductDao {
    //查询最新商品
    List<Product> findNew() throws SQLException;
    //查询最热商品
    List<Product> findIsHot() throws SQLException;
}
