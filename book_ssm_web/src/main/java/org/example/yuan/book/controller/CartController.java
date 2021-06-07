package org.example.yuan.book.controller;

import org.example.yuan.book.pojo.Book;
import org.example.yuan.book.pojo.Cart;
import org.example.yuan.book.pojo.CartItem;
import org.example.yuan.book.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping("cart")
public class CartController {

    @Autowired
    private BookService bookService;

    @RequestMapping("show")
    public String show() {
        return "cart/cart";
    }

    @RequestMapping("/add")
    public void add(int id, HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 存入session中
        // 获取图书的信息
        Book book = bookService.findById(id);
        // 把图书信息转换成cartItem项
        CartItem cartItem = new CartItem(book.getId(), book.getImg_path(), book.getName(), 1, book.getPrice(), book.getPrice());
        // 添加cart.addItem项
        Cart cart = (Cart) request.getSession().getAttribute("cart");
        if (cart == null) {
            cart = new Cart();
            request.getSession().setAttribute("cart", cart);
        }
        cart.addItem(cartItem);

        response.sendRedirect(request.getHeader("Referer"));
        // return "redirect:" + request.getHeader("Referer");

    }

    @RequestMapping("/sub")
    public void sub(int id, HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 存入session中
        // 获取图书的信息
        Book book = bookService.findById(id);
        // 把图书信息转换成cartItem项
        CartItem cartItem = new CartItem(book.getId(), book.getImg_path(), book.getName(), 1, book.getPrice(), book.getPrice());

        // 添加cart.addItem项
        Cart cart = (Cart) request.getSession().getAttribute("cart");

        cart.removeOneItem(cartItem);

        response.sendRedirect(request.getHeader("Referer"));
    }

    @RequestMapping("/delete")
    public void delete(int id, HttpServletRequest request, HttpServletResponse response) throws IOException {
        Cart cart = (Cart) request.getSession().getAttribute("cart");

        if (cart != null) {
            cart.deleteItem(id);
            response.sendRedirect(request.getHeader("Referer"));
        }

    }

    @RequestMapping("/clear")
    public void clear(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Cart cart = (Cart) request.getSession().getAttribute("cart");
        if (cart != null) {
            // 清空
            cart.clear();
            response.sendRedirect(request.getHeader("Referer"));
        }
    }
}
