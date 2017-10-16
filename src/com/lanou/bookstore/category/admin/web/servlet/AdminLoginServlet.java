package com.lanou.bookstore.category.admin.web.servlet;

import com.lanou.bookstore.category.domain.Admin;
import com.lanou.bookstore.category.service.CategoryService;
import com.lanou.bookstore.category.service.impl.CategoryServiceImpl;
import com.lanou.commons.CommonUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by dllo on 17/9/22.
 */
@WebServlet("/AdminLoginServlet")
public class AdminLoginServlet extends HttpServlet {

    private CategoryService categoryService = new CategoryServiceImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

        Admin admin = CommonUtils.toBean(request.getParameterMap(), Admin.class);

        boolean b = categoryService.login(admin);

        if(b){

            request.getSession().setAttribute("admin",admin.getUsername());

            request.getRequestDispatcher("/adminjsps/admin/main.jsp").forward(request,response);

        } else {

            request.setAttribute("msg","用户名或密码错误!");

            request.getRequestDispatcher("/adminjsps/login.jsp").forward(request,response);

        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
    }
}