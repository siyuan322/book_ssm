package org.example.yuan.book.service.impl;

import com.github.pagehelper.PageHelper;
import org.example.yuan.book.dao.BookDao;
import org.example.yuan.book.dao.UserDao;
import org.example.yuan.book.pojo.Book;
import org.example.yuan.book.pojo.User;
import org.example.yuan.book.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("bookService")
public class BookServiceImpl implements BookService {

    @Autowired
    private BookDao bookDao;

    @Autowired
    private UserDao userDao;

    @Override
    public List<Book> findAll(String search, int page, int size) {
        PageHelper.startPage(page, size);
        return bookDao.findAll("%" + search + "%");
    }

    @Override
    public List<Book> findByCategory(int categoryId, int page, int size) {
        PageHelper.startPage(page, size);
        return bookDao.findByCategory(categoryId);
    }

    @Override
    public int getCategoryIdByName(String category) {
        return bookDao.getCategoryIdByName(category);
    }

    @Override
    public int bookNum(String search) {
        return bookDao.bookNum("%" + search + "%");
    }

    @Override
    public int bookNumByCategoryId(int categoryId) {
        return bookDao.bookNumByCategoryId(categoryId);
    }

    @Override
    public Book findById(int id) {
        return bookDao.findById(id);
    }

    @Override
    public List<Book> findAllBySales(User sales) {
        // 获取category_id
        int category_id = userDao.findCategoryId(sales);
        // 根据category_id获取该类别的所有书
        return bookDao.findBookByCategoryId(category_id);
    }

    @Override
    public void addBook(Book book) {
        bookDao.addBook(book);
    }

    @Override
    public void deleteBookById(int id) {
        bookDao.deleteBookById(id);
    }

    @Override
    public void updateBook(Book book) {
        bookDao.update(book);
    }

    @Override
    public int countSalesNum(int categoryId) {
        return bookDao.countSalesNum(categoryId);
    }


}
