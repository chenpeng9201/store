package com.itheima.service;

import com.itheima.domain.PageBean;
import com.itheima.domain.Product;

import java.util.List;

public interface ProductService {
    List<Product> findNew();
    List<Product> findIsHot();
    Product pinfo(String pid);
    PageBean<Product> findByPage(int currentPage,int pageSize,String cid);
}
