package org.example.yuan.book.dao;


import org.apache.ibatis.annotations.*;
import org.example.yuan.book.pojo.User;

import java.util.List;

public interface UserDao {

    // 根据用户名和密码查找用户
    @Select("select * from t_user where username = #{username} and password = #{password}")
    User login(@Param("username") String username, @Param("password") String password);

    // 用户名是否已经存在
    @Select("select * from t_user where username = #{user.username}")
    User findUserByUsername(@Param("user") User user);

    // 用户注册
    @Insert("insert into t_user(username, password, email, role) values(#{user.username}, #{user.password}, #{user.email}, #{user.role})")
    void register(@Param("user") User user);

    // 查询销售对应的类别
    @Select("select category_id from t_sales_category where sales_id = #{user.id}")
    int findCategoryId(@Param("user") User user);

    @Select("select * from t_user where role = 'sales'")
    List<User> findAllSales();

    @Insert("insert into t_user(username, password, email, role) values(#{username}, #{password}, #{email}, #{role})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    void addSales(User user);

    @Select("select * from t_user where id = #{id}")
    User findById(int id);

    @Update("update t_user set username=#{username}, password=#{password}, email=#{email} where id=#{id}")
    void updateSales(User user);

    @Delete("delete from t_user where id = #{id}")
    void deleteSales(int id);
}
