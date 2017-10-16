package com.lanou.bookstore.category.web.servlet.admin;

import com.lanou.bookstore.order.domain.Order;
import com.lanou.bookstore.order.service.OrderException;
import com.lanou.bookstore.order.service.OrderService;
import com.lanou.bookstore.order.service.impl.OrderServiceImpl;
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
@WebServlet("/AdminOrderServlet")
public class AdminOrderServlet extends BaseServlet {

    private OrderService orderService = new OrderServiceImpl();

    public String findAllOrder(HttpServletRequest request, HttpServletResponse response){

        List<Order> order = orderService.findAllOrder();

        request.setAttribute("orders", order);

        return "f:/adminjsps/admin/order/list.jsp";

    }

    public String stateOrder(HttpServletRequest request, HttpServletResponse response){

        String state = request.getParameter("state");

        List<Order> order = orderService.findOrderByState(state);

        request.setAttribute("orders", order);

        return "f:/adminjsps/admin/order/list.jsp";

    }

    public String setState(HttpServletRequest request, HttpServletResponse response){

        String oid = request.getParameter("oid");

        try {

            orderService.sendConfirm(oid);

            List<Order> order = orderService.findAllOrder();

            request.setAttribute("orders", order);

            return "f:/adminjsps/admin/order/list.jsp";

        } catch (OrderException e) {

            request.setAttribute("msg","当前不能进行这样的操作");

            return "f:/adminjsps/admin/msg.jsp";

        }

    }

}