package com.itheima.dao;

import com.itheima.domain.User;

import java.sql.SQLException;

public interface UserDao {
    //用户注册
    void register(User user) throws SQLException;
    //用户登录
    User login(String username,String password) throws SQLException;
}
