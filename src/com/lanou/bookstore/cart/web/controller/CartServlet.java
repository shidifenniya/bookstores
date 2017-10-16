package com.lanou.bookstore.cart.web.controller;


import com.lanou.bookstore.book.domain.Book;
import com.lanou.bookstore.book.service.BookService;
import com.lanou.bookstore.book.service.impl.BookServiceImpl;
import com.lanou.bookstore.cart.domain.Cart;
import com.lanou.bookstore.cart.domain.CartItem;
import com.lanou.commons.CommonUtils;
import com.lanou.servlet.BaseServlet;


import javax.servlet.annotation.WebServlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


/**
 * Created by dllo on 17/9/21.
 */
@WebServlet("/CartServlet")
public class CartServlet extends BaseServlet{

    private BookService bookService = new BookServiceImpl();

    public String add(HttpServletRequest request, HttpServletResponse response){

        String username = (String) request.getSession().getAttribute("username");

        Cart cart = (Cart) request.getSession().getAttribute(username);

        // 获取 bid
        String bid = request.getParameter("bid");

        // 创建book对象
        Book book = new Book();

        book.setBid(bid);

        book = bookService.load(book);

        int count = Integer.parseInt(request.getParameter("count"));

        // 创建 cartItem 对象
        CartItem cartItem = new CartItem();

        // 取出map
        Map<String, CartItem> map = cart.getMap();


        if(!map.isEmpty()){

            if(map.containsKey(bid)){

                Integer count1 = map.get(bid).getCount();

                count = count + count1;
            }

        }







        // --------------

        cartItem.setBook(book);

        cartItem.setCount(count);

        // 插入 cartItem
        map.put(book.getBid(),cartItem);

        // 插入 cart 对象
        cart.setMap(map);

        // 将cart对象存入 session 域中
        request.getSession().setAttribute(username, cart);

        return "/jsps/cart/list.jsp";

    }

    public String clear(HttpServletRequest request, HttpServletResponse response){

        Cart cart = new Cart();

        cart.setMap(new HashMap<>());

        String username = (String) request.getSession().getAttribute("username");

        request.getSession().setAttribute(username, cart);

        return "/jsps/cart/list.jsp";

    }

    public String delete(HttpServletRequest request, HttpServletResponse response){

        String bid = request.getParameter("bid");

        String username = (String) request.getSession().getAttribute("username");

        Cart cart = (Cart) request.getSession(true).getAttribute(username);

        Map<String, CartItem> map = cart.getMap();

        map.remove(bid);

        cart.setMap(map);

        request.getSession().setAttribute(username, cart);

        return "/jsps/cart/list.jsp";
    }

}