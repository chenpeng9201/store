package com.itheima.service.impl;

import com.itheima.dao.ProductDao;
import com.itheima.domain.PageBean;
import com.itheima.domain.Product;
import com.itheima.service.ProductService;
import com.itheima.utils.BeanFactory;

import java.sql.SQLException;
import java.util.List;

public class ProductServiceImpl implements ProductService {
    //Bean工厂获取持久层接口实现类对象
    private ProductDao productDao = BeanFactory.newInstance(ProductDao.class);

    //查找最新商品
    @Override
    public List<Product> findNew() {
        List<Product> productListNew = null;
        try {
            productListNew = productDao.findNew();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productListNew;
    }


    //查找最热商品
    @Override
    public List<Product> findIsHot() {
        List<Product> productListHot = null;
        try {
            productListHot = productDao.findIsHot();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productListHot;
    }

    //展示商品详情
    @Override
    public Product pinfo(String pid) {
        Product product = null;
        try {
            product = productDao.pinfo(pid);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return product;
    }

    //分页查询分类下的商品
    @Override
    public PageBean<Product> findByPage(int currentPage, int pageSize, String cid) {
        PageBean<Product> pb = new PageBean<>();
        try {
            List<Product> products = productDao.productList(currentPage, pageSize, cid);
            Long totalCount = productDao.totalCount(cid);
            pb.setCurrentPage(currentPage);
            pb.setList(products);
            pb.setPageSize(pageSize);
            pb.setTotalCount(totalCount);
            double ceil = Math.ceil(totalCount * 1.0 / pageSize);
            pb.setTotalPage((int)ceil);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pb;
    }
}
