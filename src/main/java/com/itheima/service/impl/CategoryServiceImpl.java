package com.itheima.service.impl;

import com.itheima.dao.CategoryDao;
import com.itheima.domain.Category;
import com.itheima.service.CategoryService;
import com.itheima.utils.BeanFactory;

import java.sql.SQLException;
import java.util.List;

public class CategoryServiceImpl implements CategoryService {
    //使用bean工厂获取持久层实现类对象
    private CategoryDao categoryDao = BeanFactory.newInstance(CategoryDao.class);
    @Override
    public List<Category> findAll() {
        List<Category> categoryList = null;
        try {
            categoryList = categoryDao.findAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categoryList;
    }
}
