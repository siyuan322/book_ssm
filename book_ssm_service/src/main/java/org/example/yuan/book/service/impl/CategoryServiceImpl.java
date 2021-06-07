package org.example.yuan.book.service.impl;

import org.example.yuan.book.dao.CategoryDao;
import org.example.yuan.book.pojo.Category;
import org.example.yuan.book.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("categoryService")
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryDao categoryDao;

    @Override
    public List<Category> findAll() {
        return categoryDao.findAll();
    }
}
