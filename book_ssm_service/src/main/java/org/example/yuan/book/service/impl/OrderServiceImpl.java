package org.example.yuan.book.service.impl;

import org.example.yuan.book.dao.BookDao;
import org.example.yuan.book.dao.OrderDao;
import org.example.yuan.book.dao.OrderItemDao;
import org.example.yuan.book.pojo.*;
import org.example.yuan.book.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service("orderService")
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private OrderItemDao orderItemDao;

    @Autowired
    private BookDao bookDao;

    @Override
    public List<OrderItem> create(Cart cart, int userId) {
        // 订单号
        String orderId = System.currentTimeMillis() + "" + userId;

        Order order = new Order(orderId, new Date(), cart.getTotalPrice(), 1, userId);
        // 保存订单
        orderDao.create(order);

        List<OrderItem> orderItemList = new ArrayList<>();
        // 遍历购物车中每一个商品项
        for (Map.Entry<Integer, CartItem> entry : cart.getItems().entrySet()) {
            CartItem cartItem = entry.getValue();

            OrderItem orderItem = new OrderItem(cartItem.getId(), cartItem.getName(), cartItem.getCount(), cartItem.getPrice(), cartItem.getTotalPrice(), orderId);
            orderItemDao.create(orderItem);

            orderItemList.add(orderItem);

            Book book = bookDao.findById(cartItem.getId());
            book.setSales(book.getSales() + cartItem.getCount());
            book.setStock(book.getStock() - cartItem.getCount());
            bookDao.update(book);
        }
        // 清空购物车
        cart.clear();

        return orderItemList;
    }

    @Override
    public Order findById(String id) {
        return orderDao.findById(id);
    }

    @Override
    public List<Order> findAll(Integer id) {
        return orderDao.findAll(id);
    }

    @Override
    public List<OrderItem> findAllItems(String orderId) {
        return orderItemDao.findAllItemsByOrderId(orderId);
    }
}
