package com.lanou.bookstore.category.web.servlet.admin;

import com.lanou.bookstore.book.domain.Book;
import com.lanou.bookstore.book.service.BookService;
import com.lanou.bookstore.book.service.impl.BookServiceImpl;
import com.lanou.bookstore.category.domain.Category;
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
 * Created by dllo on 17/9/23.
 */
@WebServlet("/AdminBookServlet")
public class AdminBookServlet extends BaseServlet {

    private BookService bookService = new BookServiceImpl();

    private CategoryService categoryService = new CategoryServiceImpl();

    public String findAdd(HttpServletRequest request, HttpServletResponse response){

        List<Book> bookList = bookService.findAll();

        request.setAttribute("bookList",bookList);

        return "f:/adminjsps/admin/book/list.jsp";

    }

    public String load(HttpServletRequest request, HttpServletResponse response){

        Book book = CommonUtils.toBean(request.getParameterMap(), Book.class);

        book = bookService.load(book);

        request.setAttribute("book",book);

        List<Category> category = categoryService.findCategory();

        request.setAttribute("category",category);

        return "/adminjsps/admin/book/desc.jsp";

    }

    /**
     * 添加图书功能第一步,遍历信息
     * @param request
     * @param response
     * @return
     */

    public String addPre(HttpServletRequest request, HttpServletResponse response){

        List<Category> category = categoryService.findCategory();

        request.setAttribute("category",category);

        return "/adminjsps/admin/book/add.jsp";

    }

    /**
     * 删除书目
     * @param request
     * @param response
     * @return
     */

    public String del(HttpServletRequest request, HttpServletResponse response){

        Book book = CommonUtils.toBean(request.getParameterMap(), Book.class);

        String bid = book.getBid();

        categoryService.del(bid);

        List<Book> bookList = bookService.findAll();

        request.setAttribute("bookList",bookList);

        return "f:/adminjsps/admin/book/list.jsp";

    }

    /**
     * 修改书的信息
     * @param request
     * @param response
     * @return
     */

    public String mod(HttpServletRequest request, HttpServletResponse response){

        Book book = CommonUtils.toBean(request.getParameterMap(), Book.class);

        categoryService.edit(book);

        List<Book> bookList = bookService.findAll();

        request.setAttribute("bookList",bookList);

        return "f:/adminjsps/admin/book/list.jsp";

    }

}