package com.itheima.service;

import com.itheima.domain.Category;

import java.util.List;

public interface CategoryService {
    //查找所有分类
    List<Category>  findAll();
}
