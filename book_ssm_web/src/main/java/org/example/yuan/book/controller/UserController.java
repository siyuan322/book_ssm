package org.example.yuan.book.controller;

import org.example.yuan.book.pojo.User;
import org.example.yuan.book.service.UserService;
import org.example.yuan.book.util.RandomValidateCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller("userController")
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    // 用户登录
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginView() {
        return "user/login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ModelAndView login(String username, String password, HttpServletRequest request) {
        ModelAndView mv = new ModelAndView();
        User user = userService.login(username, password);
        if (user == null || !"user".equals(user.getRole())) {
            mv.setViewName("user/login");
            mv.addObject("errMsg", "用户名或密码错误");
        } else {
            mv.setViewName("index");
            mv.addObject("user", user);
            // 将用户信息存进session中
            HttpSession session = request.getSession();
            session.setAttribute("loginUser", user);
        }
        return mv;
    }

    // 用户注册
    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String registerView() {
        return "user/register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ModelAndView register(User registerUser) {
        ModelAndView mv = new ModelAndView();

        // 该用户名唯一
        if (userService.register(registerUser)) {
            mv.setViewName("user/login");
        } else {
            mv.setViewName("user/register");
            mv.addObject("errMsg", "该用户名已被使用");
        }
        return mv;
    }

    // 验证码
    @RequestMapping(value = "/checkCode")
    public void checkCode(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //设置相应类型,告诉浏览器输出的内容为图片
        response.setContentType("image/jpeg");
        //设置响应头信息，告诉浏览器不要缓存此内容
        response.setHeader("pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expire", 0);
        RandomValidateCode randomValidateCode = new RandomValidateCode();
        try {
            randomValidateCode.getRandcode(request, response);//输出图片方法
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("/exit")
    public String exit(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.removeAttribute("loginUser");

        // 重定向
        return "redirect:/book/findAll";
    }

}
