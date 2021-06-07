package org.example.yuan.book.controller;

import org.example.yuan.book.pojo.Cart;
import org.example.yuan.book.pojo.Order;
import org.example.yuan.book.pojo.OrderItem;
import org.example.yuan.book.pojo.User;
import org.example.yuan.book.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @RequestMapping("/create")
    public ModelAndView create(HttpServletRequest request, HttpServletResponse response) {
        // 获取Cart
        Cart cart = (Cart) request.getSession().getAttribute("cart");
        // 获取用户id
        User loginUser = (User) request.getSession().getAttribute("loginUser");

        Integer userId = loginUser.getId();

        // 生成订单
        List<OrderItem> orderItems = orderService.create(cart, userId);
        // Order order = orderService.findById(order_id);

        // 发送邮件
        //MailUtils.sendMail(loginUser.getEmail(), "请确认订单", "订单确认");
        //System.out.println("发送成功。。");

        ModelAndView mv = new ModelAndView();
        mv.addObject("orderItems", orderItems);
        mv.setViewName("order/orderItem");

        return mv;
    }

    @RequestMapping("/show")
    public ModelAndView show(HttpServletRequest request) {
        // 获取用户信息
        HttpSession session = request.getSession();
        User loginUser = (User) session.getAttribute("loginUser");
        ModelAndView mv = new ModelAndView();
        // 把该用户的所有订单信息都查出来
        List<Order> orderList = orderService.findAll(loginUser.getId());
        mv.addObject("orderList", orderList);
        mv.setViewName("order/order");
        return mv;
    }

    @RequestMapping("/showOrderItem")
    public ModelAndView showOrderItem(String orderId) {
        ModelAndView mv = new ModelAndView();
        // 查出该订单的所有详情
        List<OrderItem> orderItems = orderService.findAllItems(orderId);
        mv.addObject("orderItems", orderItems);
        mv.setViewName("order/orderItem");

        return mv;
    }
}
