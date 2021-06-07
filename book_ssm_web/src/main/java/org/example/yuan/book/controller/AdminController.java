package org.example.yuan.book.controller;

import org.example.yuan.book.pojo.Book;
import org.example.yuan.book.pojo.User;
import org.example.yuan.book.service.BookService;
import org.example.yuan.book.service.CategorySalesService;
import org.example.yuan.book.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService userService;

    @Autowired
    private BookService bookService;

    @Autowired
    private CategorySalesService categorySalesService;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginView() {
        return "admin/login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ModelAndView login(String username, String password, HttpServletRequest request) {
        ModelAndView mv = new ModelAndView();
        User user = userService.login(username, password);
        if (user == null || "user".equals(user.getRole())) {
            mv.setViewName("admin/login");
            mv.addObject("errMsg", "用户名或密码错误");
        } else {
            mv.setViewName("admin/index");
            mv.addObject("admin", user);
            // 将用户信息存进session中
            HttpSession session = request.getSession();
            session.setAttribute("loginUser", user);
        }
        return mv;
    }

    @RequestMapping("book/findAll")
    public ModelAndView findAll(HttpServletRequest request) {
        // 获取当前的销售
        HttpSession session = request.getSession();
        User sales = (User) session.getAttribute("loginUser");
        // 查到该销售负责的类别对应的所有商品
        List<Book> bookList = bookService.findAllBySales(sales);

        ModelAndView mv = new ModelAndView();
        mv.setViewName("admin/product-list");
        mv.addObject("bookList", bookList);
        return mv;
    }

    @RequestMapping("book/add")
    public String bookAddView() {
        return "admin/product-add";
    }

    @RequestMapping(value = "book/add", method = RequestMethod.POST)
    public String bookAdd(String name, int price, String author,
                          int sales, int stock, MultipartFile uploadFile, String brief, HttpServletRequest request) throws IOException {
        // 定义文件名
        String fileName = "";
        // 获取原始文件名
        String uploadFileName = uploadFile.getOriginalFilename();
        // 把文件加上随机数，防止文件重复
        String uuid = UUID.randomUUID().toString().replace("-", "").toUpperCase();

        fileName = uuid + "_" + uploadFileName;
        ServletContext context = request.getServletContext();
        String basePath = context.getRealPath("/img");

        // 使用MulitpartFile接口中方法，把上传的文件写到指定位置
        uploadFile.transferTo(new File(basePath + "/" + fileName));

        // 获取目录id
        User loginUser = (User) request.getSession().getAttribute("loginUser");
        int categoryId = userService.getSalesCategoryId(loginUser);
        Book book = new Book(name, price, author, sales, stock, "img/" + fileName, brief, categoryId);
        bookService.addBook(book);

        // 返回管理页面
        return "redirect:/admin/book/findAll";
    }

    @RequestMapping(value = "book/delete", method = RequestMethod.GET)
    public String bookDelete(int id) {
        bookService.deleteBookById(id);

        // 返回管理页面
        return "redirect:/admin/book/findAll";
    }

    @RequestMapping(value = "book/edit", method = RequestMethod.GET)
    public ModelAndView bookEditView(int id) {
        ModelAndView mv = new ModelAndView();
        Book book = bookService.findById(id);
        mv.addObject("book", book);
        mv.setViewName("admin/product-edit");
        // 返回管理页面
        return mv;
    }

    @RequestMapping(value = "book/edit", method = RequestMethod.POST)
    public String bookEdit(int id, String name, int price, String author, int stock, String brief) {
        Book book = bookService.findById(id);
        book.setName(name);
        book.setPrice(price);
        book.setAuthor(author);
        book.setStock(stock);
        book.setBrief(brief);
        bookService.updateBook(book);

        // 返回管理页面
        return "redirect:/admin/book/findAll";

    }

    // 用户管理
    // 查询所有
    @RequestMapping("sales/findAll")
    public ModelAndView findAllUser() {
        ModelAndView mv = new ModelAndView();
        List<User> userList = userService.findAllSales();
        mv.setViewName("admin/user-list");
        mv.addObject("userList", userList);

        return mv;
    }

    // 新增销售

    @RequestMapping(value = "sales/add", method = RequestMethod.GET)
    public String addSalesView() {
        return "admin/user-add";
    }

    @RequestMapping(value = "sales/add", method = RequestMethod.POST)
    public String addSales(User user, int category_id) {
        user.setRole("sales");
        boolean flag = userService.addSales(user);
        if (flag == false) {
            return "admin/user-add";
        }
        categorySalesService.add(category_id, user.getId());
        return "redirect:/admin/sales/findAll";
    }

    @RequestMapping(value = "sales/edit", method = RequestMethod.GET)
    public ModelAndView editSalesView(int id) {
        ModelAndView mv = new ModelAndView();
        User sales = userService.findById(id);
        int category_id = userService.getSalesCategoryId(sales);
        mv.addObject("sales", sales);
        mv.addObject("category_id", category_id);
        mv.setViewName("admin/user-edit");
        return mv;
    }

    @RequestMapping(value = "sales/edit", method = RequestMethod.POST)
    public String editSales(User user, int category_id) {
        userService.updateSales(user);
        categorySalesService.update(category_id, user.getId());
        return "redirect:/admin/sales/findAll";
    }

    @RequestMapping(value = "sales/delete", method = RequestMethod.GET)
    public String deleteSales(int id) {
        userService.deleteSales(id);
        return "redirect:/admin/sales/findAll";
    }
}
