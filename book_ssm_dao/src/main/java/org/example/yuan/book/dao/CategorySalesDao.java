package org.example.yuan.book.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

public interface CategorySalesDao {

    @Insert("insert into t_sales_category(sales_id, category_id) values(#{sales_id}, #{category_id})")
    void add(@Param("category_id") int category_id, @Param("sales_id") int sales_id);

    @Update("update t_sales_category set category_id=#{category_id} where sales_id=#{sales_id}")
    void update(@Param("category_id") int category_id, @Param("sales_id") Integer sales_id);
}
