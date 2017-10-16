package com.lanou.bookstore.user.web.controller;

import com.lanou.bookstore.user.service.UserService;
import com.lanou.bookstore.user.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * Created by dllo on 17/9/27.
 */
@WebServlet("/UsernameServlet")
public class UsernameServlet extends HttpServlet {

    private UserService userService  = new UserServiceImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

        String username = request.getParameter("username");

        Map<String, String> map = userService.checkUsername(username);

        if(!map.isEmpty()){

            response.getWriter().print("{\""+"nameSize"+"\":\""+map.get("nameSize")+"\",\"nameNum\":\"0\"}");

            System.out.println("{\""+"nameSize"+"\":\""+map.get("nameSize")+"\",\"nameNum\":\"0\"}");

        } else {

            response.getWriter().print("{\"nameSize\":\"用户名可以使用\",\"nameNum\":\"1\"}");

            System.out.println("{\"nameSize\":\"用户名可以使用\",\"nameNum\":\"1\"}");
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
    }
}