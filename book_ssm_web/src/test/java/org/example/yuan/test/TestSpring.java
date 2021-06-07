package org.example.yuan.test;

import org.example.yuan.book.pojo.User;
import org.example.yuan.book.service.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestSpring {
    public static void main(String[] args) {
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserService service = ac.getBean("userService", UserService.class);
        User user = new User();
        User login = service.login("admin", "admin");
        System.out.println(login);
    }
}
