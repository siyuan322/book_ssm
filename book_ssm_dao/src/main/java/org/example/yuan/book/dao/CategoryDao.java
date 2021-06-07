package org.example.yuan.book.dao;

import org.apache.ibatis.annotations.Select;
import org.example.yuan.book.pojo.Category;

import java.util.List;

public interface CategoryDao {

    @Select("select * from t_category")
    List<Category> findAll();
}
