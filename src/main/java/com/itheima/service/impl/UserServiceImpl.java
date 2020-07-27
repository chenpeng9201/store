package com.itheima.service.impl;

import com.itheima.dao.UserDao;
import com.itheima.domain.User;
import com.itheima.service.UserService;
import com.itheima.utils.BeanFactory;

import java.sql.SQLException;

public class UserServiceImpl implements UserService {
    //使用bean工厂获取业务层接口实现类
    UserDao userDao = BeanFactory.newInstance(UserDao.class);
    @Override
    public void register(User user) {
        //用户注册
        try {
            userDao.register(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public User login(String username, String password) {
        //用户登录
        User user = new User();
        try {
             user = userDao.login(username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }
}
