package org.example.yuan.book.controller;

import com.github.pagehelper.PageInfo;
import org.example.yuan.book.pojo.Book;
import org.example.yuan.book.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.io.UnsupportedEncodingException;
import java.util.List;

@Controller("bookController")
@RequestMapping("/book")
public class BookController {

    @Autowired
    private BookService bookService;

    @RequestMapping("/findAll")
    public ModelAndView findAll(@RequestParam(value = "category", required = false, defaultValue = "") String category,
                                @RequestParam(value = "page", required = true, defaultValue = "1") Integer page,
                                @RequestParam(value = "size", required = true, defaultValue = "4") Integer size,
                                @RequestParam(value = "search", required = true, defaultValue = "") String search) throws UnsupportedEncodingException {
        ModelAndView mv = new ModelAndView();
        // 将 category返回
        // 获取符合要求的搜索
        List<Book> books = null;
        int bookNum = 0;
        if ("".equals(category) || new String(category.getBytes("iso-8859-1"), "UTF-8").equals("全部")) {
            category = "全部";
            search = new String(search.getBytes("iso-8859-1"), "UTF-8");
            books = bookService.findAll(search, page, size);
            bookNum = bookService.bookNum(search);
        } else {
            category = new String(category.getBytes("iso-8859-1"), "UTF-8");
            // 获取分类的id
            int categoryId = bookService.getCategoryIdByName(category);
            books = bookService.findByCategory(categoryId, page, size);
            bookNum = bookService.bookNumByCategoryId(categoryId);
        }
        mv.addObject("category", category);
        mv.addObject("search", search);

        // 获取总页数
        int totalPage = (bookNum % size == 0) ? bookNum / size : bookNum / size + 1;
        mv.addObject("bookNum", bookNum);
        mv.addObject("totalPage", totalPage);
        mv.addObject("currentPage", page);

        // 封装PageInfo对象
        PageInfo pageInfo = new PageInfo(books);
        mv.setViewName("index");
        mv.addObject("pageInfo", pageInfo);
        return mv;
    }
}
