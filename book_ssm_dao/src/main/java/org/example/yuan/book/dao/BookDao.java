package org.example.yuan.book.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.example.yuan.book.pojo.Book;

import java.util.List;

public interface BookDao {

    @Select("select * from t_book where name like #{search}  or author like #{search}")
    List<Book> findAll(String search);

    @Select("select id from t_category where name = #{category}")
    int getCategoryIdByName(String category);

    @Select("select * from t_book where category_id = #{categoryId}")
    List<Book> findByCategory(int categoryId);

    @Select("select count(*) from t_book where name like #{search} or author like #{search}")
    int bookNum(String search);

    @Select("select count(*) from t_book where category_id = #{categoryId}")
    int bookNumByCategoryId(int categoryId);

    @Select("select * from t_book where id = #{id}")
    Book findById(int id);

    @Update("update t_book set name=#{name}, price=#{price}, author=#{author}, sales=#{sales}, " +
            "stock=#{stock}, brief=#{brief}, category_id=#{category_id} where id=#{id}")
    void update(Book book);

    @Select("select * from t_book where category_id = #{category_id}")
    List<Book> findBookByCategoryId(int category_id);

    @Insert("insert into t_book(name, price, author, sales, stock, img_path, brief, category_id) " +
            "values(#{name}, #{price}, #{author}, #{sales}, #{stock}, #{img_path}, #{brief}, #{category_id})")
    void addBook(Book book);

    @Delete("delete from t_book where id = #{id}")
    void deleteBookById(int id);

    @Select("select sum(sales) from t_book where category_id = #{category_id}")
    int countSalesNum(int category_id);
}
