package com.itheima.dao;

import com.itheima.domain.Category;

import java.sql.SQLException;
import java.util.List;

public interface CategoryDao {
    //查询所有分类
    List<Category> findAll() throws SQLException;
}
