package org.example.yuan.book.service.impl;

import org.example.yuan.book.dao.CategorySalesDao;
import org.example.yuan.book.service.CategorySalesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("categorySalesService")
public class CategorySalesServiceImpl implements CategorySalesService {

    @Autowired
    private CategorySalesDao categorySalesDao;

    @Override
    public void add(int category_id, int user_id) {
        categorySalesDao.add(category_id, user_id);
    }

    @Override
    public void update(int category_id, Integer user_id) {
        categorySalesDao.update(category_id, user_id);
    }
}
