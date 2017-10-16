package com.lanou.bookstore.book.web.controller;

import com.lanou.bookstore.book.domain.Book;
import com.lanou.bookstore.category.domain.Category;
import com.lanou.bookstore.book.service.BookService;
import com.lanou.bookstore.book.service.impl.BookServiceImpl;
import com.lanou.commons.CommonUtils;
import com.lanou.servlet.BaseServlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by dllo on 17/9/21.
 */
@WebServlet("/BookServlet")
public class BookServlet extends BaseServlet {

    private BookService bookService = new BookServiceImpl();

    public String findAll(HttpServletRequest request, HttpServletResponse response){

        List<Book> bookList = bookService.findAll();

        request.setAttribute("list", bookList);

        return "f:jsps/book/list.jsp";

    }

    public String findByCategory(HttpServletRequest request, HttpServletResponse response){

        Category category = CommonUtils.toBean(request.getParameterMap(), Category.class);

        List<Book> bookList = bookService.findByCategory(category);

        request.setAttribute("list", bookList);

        return "f:jsps/book/list.jsp";

    }

    public String load(HttpServletRequest request, HttpServletResponse response){

        Book book = CommonUtils.toBean(request.getParameterMap(), Book.class);

        Book load = bookService.load(book);

        request.setAttribute("book", load);

        return "f:jsps/book/desc.jsp";

    }

}