package org.example.yuan.book.controller;

import org.example.yuan.book.pojo.Book;
import org.example.yuan.book.pojo.Category;
import org.example.yuan.book.pojo.User;
import org.example.yuan.book.service.BookService;
import org.example.yuan.book.service.CategoryService;
import org.example.yuan.book.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin/data")
public class DataController {

    @Autowired
    private BookService bookService;

    @Autowired
    private UserService userService;

    @Autowired
    private CategoryService categoryService;

    @RequestMapping("/sales/show")
    public ModelAndView dataShow(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView();
        User loginUser = (User) request.getSession().getAttribute("loginUser");
        // 总数目
        int categoryId = userService.getSalesCategoryId(loginUser);
        int salesNum = bookService.countSalesNum(categoryId);
        mv.addObject("salesNum", salesNum);

        // 每件商品的详情
        List<Book> bookList = bookService.findAllBySales(loginUser);
        mv.addObject("bookList", bookList);
        mv.setViewName("admin/data-show");
        return mv;
    }

    @RequestMapping("/category/show")
    public ModelAndView categoryDataShow(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView();
        List<Category> categories = categoryService.findAll();

        Map<String, Integer> salesNumMap = new HashMap();
        int sum = 0;
        for (Category category : categories) {
            int salesNum = bookService.countSalesNum(category.getId());
            salesNumMap.put(category.getName(), salesNum);
            sum += salesNum;
        }
        mv.addObject("sum", sum);
        mv.setViewName("admin/category-data-show");
        mv.addObject("salesNumMap", salesNumMap);
        return mv;
    }
}
