package org.example.yuan.book.service;

import org.example.yuan.book.pojo.Cart;
import org.example.yuan.book.pojo.Order;
import org.example.yuan.book.pojo.OrderItem;

import java.util.List;

public interface OrderService {
    List<OrderItem> create(Cart cart, int userId);

    Order findById(String id);

    List<Order> findAll(Integer id);

    List<OrderItem> findAllItems(String orderId);
}
