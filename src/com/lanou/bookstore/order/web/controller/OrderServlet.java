package com.lanou.bookstore.order.web.controller;

import com.lanou.bookstore.cart.domain.Cart;
import com.lanou.bookstore.cart.domain.CartItem;
import com.lanou.bookstore.order.domain.Order;
import com.lanou.bookstore.order.domain.OrderItem;
import com.lanou.bookstore.order.service.OrderException;
import com.lanou.bookstore.order.service.OrderService;
import com.lanou.bookstore.order.service.impl.OrderServiceImpl;
import com.lanou.bookstore.user.service.UserService;
import com.lanou.bookstore.user.service.impl.UserServiceImpl;
import com.lanou.commons.CommonUtils;
import com.lanou.servlet.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by dllo on 17/9/22.
 */
@WebServlet("/OrderServlet")
public class OrderServlet extends BaseServlet{

    private UserService userService = new UserServiceImpl();

    private OrderService orderService = new OrderServiceImpl();

    public String add(HttpServletRequest request, HttpServletResponse response){

        Order order = new Order();

        String username = (String) request.getSession(true).getAttribute("username");

        if(username == null){

            request.setAttribute("msg","请先登录再购物吧!");

            return "f:/jsps/msg.jsp";

        }

        String total = request.getParameter("sum");

        // 将总价,插入order对象
        order.setTotal(total);

        // 获取购物车
        Cart cart = (Cart) request.getSession(true).getAttribute(username);

        //获取购物车Map
        Map<String, CartItem> map = cart.getMap();

        //Map判空
        if(map.isEmpty()){

            request.setAttribute("msg","购物车里没有任何东西哦~ 请先逛逛吧!");

            return "f:/jsps/msg.jsp";

        }

        //插入 uid
        order.setUid(userService.findUid(username));

        //调用add方法,填入空缺的数值
        Order orderNew = orderService.add(order);

        // 调用map封装方法,将 OrderItem 封装到 Order 中
        Order ListOrder = orderService.mapList(map, orderNew);

        //移除购物车
        request.getSession().removeAttribute(username);

        //赋予一个新map
        cart.setMap(new HashMap<>());

        request.getSession().setAttribute(username,cart);

        request.setAttribute("order", ListOrder);

        return "f:/jsps/order/desc.jsp";

    }

    /**
     * 查询用户订单(我的订单)
     * @param request
     * @param response
     * @return
     */
    public String myOrders(HttpServletRequest request, HttpServletResponse response){

        // 获取用户名
        String username = (String)request.getSession().getAttribute("username");

        // 未登录的用户不能查看订单!
        if(username == null){

            request.setAttribute("msg","请先登录后再查看订单!");

            return "f:/jsps/msg.jsp";

        }

        // 使用用户名获取 uid
        String uid = userService.findUid(username);

        List<Order> orders = orderService.orderListByUid(uid);

        request.setAttribute("orders", orders);

        return "f:/jsps/order/list.jsp";
    }

    /**
     *加载订单(按 id 查询)
     * @param request
     * @param response
     * @return
     */
    public String load(HttpServletRequest request, HttpServletResponse response){

        String oid = request.getParameter("oid");

        Order order = orderService.load(oid);

        request.setAttribute("order", order);

        return "f:/jsps/order/desc.jsp";

    }

    /**
     * 确认收货
     * @param request
     * @param response
     * @return
     */

    public String confirm(HttpServletRequest request, HttpServletResponse response){

        // 获取 oid
        String oid = request.getParameter("oid");

        try {

            orderService.confirm(oid);

            request.setAttribute("msg", "确认收货成功,完成交易!");

            return "f:jsps/msg.jsp";


        } catch (OrderException e) {

            request.setAttribute("msg", e.getMessage());

            return "f:jsps/msg.jsp";

        }

    }

    /**
     * 支付并提交订单
     * @param request
     * @param response
     * @return
     */

    public String pay(HttpServletRequest request, HttpServletResponse response){

        String oid = request.getParameter("oid");

        String address = request.getParameter("address");

        String pay = request.getParameter("pd_FrpId");

        Order order = orderService.load(oid);

        if(address.isEmpty()){

            request.setAttribute("msgAddress", "支付失败,订单地址不能为空.");

            request.setAttribute("order", order);

            return "f:/jsps/order/desc.jsp";

        }

        if(pay.equals("balance")){

            String usename = (String) request.getSession().getAttribute("username");

            String uid = userService.findUid(usename);

            try {

                orderService.balancePay(oid,uid);

                String balance = userService.findBalance(uid);

                request.getSession(true).setAttribute("balance",balance);


            } catch (OrderException e) {

                request.setAttribute("msgPay", e.getMessage());

                request.setAttribute("order", order);

                return "f:/jsps/order/desc.jsp";

            }

        }

        try {

            orderService.payConfirm(oid,address);

            request.setAttribute("msg", "支付成功,您的订单正在准备发货!");

            return "f:jsps/user/rechargeSuccess.jsp";

        } catch (OrderException e) {

            request.setAttribute("msg", e.getMessage());

            return "f:jsps/msg.jsp";

        }

    }

    /**
     * 进入购物车判断
     * @param request
     * @param response
     * @return
     */

    public String myCart(HttpServletRequest request, HttpServletResponse response){

        String username = (String) request.getSession(true).getAttribute("username");

        if(username == null){

            request.setAttribute("msg","您还没有登录,查看不了购物车哦!");

            return "f:jsps/msg.jsp";

        }

        return "f:jsps/cart/list.jsp";

    }

}