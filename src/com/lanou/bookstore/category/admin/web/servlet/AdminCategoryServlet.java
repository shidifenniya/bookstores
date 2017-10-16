package com.lanou.bookstore.category.admin.web.servlet;

import com.lanou.bookstore.category.domain.Category;
import com.lanou.bookstore.category.service.CategoryException;
import com.lanou.bookstore.category.service.CategoryService;
import com.lanou.bookstore.category.service.impl.CategoryServiceImpl;
import com.lanou.commons.CommonUtils;
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
@WebServlet("/AdminCategoryServlet")
public class AdminCategoryServlet extends BaseServlet {

    private CategoryService categoryService = new CategoryServiceImpl();

    public String findAll(HttpServletRequest request, HttpServletResponse response){

        List<Category> category = categoryService.findCategory();

        request.setAttribute("category",category);

        return "f:/adminjsps/admin/category/list.jsp";

    }

    public String add(HttpServletRequest request, HttpServletResponse response){

        Category category = CommonUtils.toBean(request.getParameterMap(), Category.class);

        categoryService.add(category);

        request.setAttribute("msg","添加完成!");

        return "f:/adminjsps/admin/category/add.jsp";

    }

    public String delete(HttpServletRequest request, HttpServletResponse response){

        String cid = request.getParameter("cid");

        try {

            categoryService.delete(cid);

            request.setAttribute("msg", "删除成功");

            return "f:/adminjsps/admin/msg.jsp";

        } catch (CategoryException e) {

            request.setAttribute("msg", e.getMessage());

            return "f:/adminjsps/admin/msg.jsp";

        }

    }

    public String editPre(HttpServletRequest request, HttpServletResponse response){

        String cid = request.getParameter("cid");

        Category category = categoryService.findByCid(cid);

        request.setAttribute("category",category);

        return "f:/adminjsps/admin/category/mod.jsp";

    }

    public String edit(HttpServletRequest request, HttpServletResponse response){

        Category category = CommonUtils.toBean(request.getParameterMap(), Category.class);

        categoryService.edit(category);

        List<Category> categoryNew = categoryService.findCategory();

        request.setAttribute("category",categoryNew);

        return "f:/adminjsps/admin/category/list.jsp";

    }


}