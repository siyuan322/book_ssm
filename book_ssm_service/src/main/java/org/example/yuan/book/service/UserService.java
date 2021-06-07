package org.example.yuan.book.service;

import org.example.yuan.book.pojo.User;

import java.util.List;

public interface UserService {

    // 登录
    User login(String username, String password);

    // 根据用户名查找用户
    boolean register(User user);

    // 销售负责的目录id
    int getSalesCategoryId(User user);

    List<User> findAllSales();

    boolean addSales(User user);

    User findById(int id);

    void updateSales(User user);

    void deleteSales(int id);
}
