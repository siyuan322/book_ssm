package org.example.yuan.book.service;

import org.example.yuan.book.pojo.Book;
import org.example.yuan.book.pojo.User;

import java.util.List;

public interface BookService {

    List<Book> findAll(String search, int page, int size);

    List<Book> findByCategory(int categoryId, int page, int size);

    int getCategoryIdByName(String category);

    int bookNum(String search);

    int bookNumByCategoryId(int categoryId);

    Book findById(int id);

    List<Book> findAllBySales(User sales);

    void addBook(Book book);

    void deleteBookById(int id);

    void updateBook(Book book);

    int countSalesNum(int categoryId);
}
