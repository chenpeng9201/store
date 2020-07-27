package com.itheima.dao.impl;

import com.itheima.dao.UserDao;
import com.itheima.domain.User;
import com.itheima.utils.C3P0Utils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.sql.SQLException;

public class UserDaoImpl implements UserDao {
    QueryRunner queryRunner = new QueryRunner(C3P0Utils.getDataSource());
    @Override
    public void register(User user) throws SQLException {
        //用户注册
        String sql = "insert into user values (?,?,?,?,?,?,?,?,?,?)";
        Object[] params = {
                user.getUid(),user.getUsername(),user.getPassword(),
                user.getName(),user.getEmail(),user.getBirthday(),
                user.getGender(),user.getState(),user.getCode(),user.getRemark()
        };
        queryRunner.update(sql,params);
    }

    @Override
    public User login(String username, String password) throws SQLException {
        //用户登录
        String sql = "select * from user where username=? and password=?";
        User user = queryRunner.query(sql, new BeanHandler<User>(User.class), username, password);
        return user;
    }
}
