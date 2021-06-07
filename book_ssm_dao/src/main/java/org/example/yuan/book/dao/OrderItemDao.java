package org.example.yuan.book.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.example.yuan.book.pojo.OrderItem;

import java.util.List;

public interface OrderItemDao {
    @Insert("insert into t_order_item values(#{id}, #{name}, #{count}, #{price}, #{total_price}, #{order_id})")
    void create(OrderItem orderItem);

    @Select("select * from t_order_item where order_id = #{orderId}")
    List<OrderItem> findAllItemsByOrderId(String orderId);
}
