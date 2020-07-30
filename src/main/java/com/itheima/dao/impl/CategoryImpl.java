package com.itheima.dao.impl;

import com.itheima.dao.CategoryDao;
import com.itheima.domain.Category;
import com.itheima.utils.C3P0Utils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

public class CategoryImpl implements CategoryDao {
    QueryRunner qr = new QueryRunner(C3P0Utils.getDataSource());
    @Override
    public List<Category> findAll() throws SQLException {
        String sql = "select * from category";
        List<Category> categoryList = qr.query(sql, new BeanListHandler<Category>(Category.class));
        return categoryList;
    }
}
