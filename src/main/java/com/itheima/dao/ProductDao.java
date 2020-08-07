package com.itheima.dao;

import com.itheima.domain.Product;
import java.sql.SQLException;
import java.util.List;

public interface ProductDao {
    //查询最新商品
    List<Product> findNew() throws SQLException;
    //查询最热商品
    List<Product> findIsHot() throws SQLException;

    //展示商品详情
    Product pinfo(String pid) throws SQLException;

    //分页查询每个分类下的商品
    List<Product> productList(int currentPage,int pageSize,String cid) throws SQLException;

    //查询总数量
    Long totalCount(String cid) throws SQLException;
}
