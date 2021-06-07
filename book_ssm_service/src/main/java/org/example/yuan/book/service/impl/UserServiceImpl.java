package org.example.yuan.book.service.impl;


import org.example.yuan.book.dao.UserDao;
import org.example.yuan.book.pojo.User;
import org.example.yuan.book.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public User login(String username, String password) {
        return userDao.login(username, password);
    }

    @Override
    public boolean register(User user) {
        // 该用户名未被使用
        if (userDao.findUserByUsername(user) == null) {
            userDao.register(user);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int getSalesCategoryId(User user) {
        return userDao.findCategoryId(user);
    }

    @Override
    public List<User> findAllSales() {
        return userDao.findAllSales();
    }

    @Override
    public boolean addSales(User user) {
        // 该用户名未被使用
        if (userDao.findUserByUsername(user) == null) {
            userDao.addSales(user);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public User findById(int id) {
        return userDao.findById(id);
    }

    @Override
    public void updateSales(User user) {
        userDao.updateSales(user);
    }

    @Override
    public void deleteSales(int id) {
        userDao.deleteSales(id);
    }


}
