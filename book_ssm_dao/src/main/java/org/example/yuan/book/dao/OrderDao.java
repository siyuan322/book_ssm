package org.example.yuan.book.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.example.yuan.book.pojo.Order;

import java.util.List;

public interface OrderDao {
    @Insert("insert into t_order values(#{id}, #{create_time}, #{price}, #{status}, #{user_id})")
    void create(Order order);

    @Select("select * from t_order where id = #{id}")
    Order findById(String id);

    @Select("select * from t_order where user_id = #{id}")
    List<Order> findAll(Integer id);
}
