package com.itheima.service;

import com.itheima.domain.User;

public interface UserService {
    void register(User user);
    User login(String username,String password);
}
