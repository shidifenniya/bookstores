package com.lanou.bookstore.category.web.controller;

import com.lanou.bookstore.category.domain.Category;
import com.lanou.bookstore.category.service.CategoryService;
import com.lanou.bookstore.category.service.impl.CategoryServiceImpl;
import com.lanou.servlet.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by dllo on 17/9/22.
 */
@WebServlet("/CategoryServlet")
public class CategoryServlet extends BaseServlet {

    private CategoryService categoryService = new CategoryServiceImpl();

    public String categoryFindAll(HttpServletRequest request, HttpServletResponse response){

        List<Category> category = categoryService.findCategory();

        request.setAttribute("category", category);

        return "f:jsps/left.jsp";

    }

}