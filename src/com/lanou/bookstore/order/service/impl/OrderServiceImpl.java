package com.lanou.bookstore.order.service.impl;

import com.lanou.bookstore.book.domain.Book;
import com.lanou.bookstore.book.service.BookService;
import com.lanou.bookstore.book.service.impl.BookServiceImpl;
import com.lanou.bookstore.cart.domain.CartItem;
import com.lanou.bookstore.order.dao.OrderDao;
import com.lanou.bookstore.order.dao.impl.OrderDaoImpl;
import com.lanou.bookstore.order.domain.Order;
import com.lanou.bookstore.order.domain.OrderItem;
import com.lanou.bookstore.order.service.OrderException;
import com.lanou.bookstore.order.service.OrderService;
import com.lanou.bookstore.user.service.UserService;
import com.lanou.bookstore.user.service.impl.UserServiceImpl;
import com.lanou.commons.CommonUtils;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by dllo on 17/9/22.
 */
public class OrderServiceImpl implements OrderService{

    private BookService bookService = new BookServiceImpl();

    private OrderDao orderDao = new OrderDaoImpl();

    private UserService userService = new UserServiceImpl();

    @Override
    public Order add(Order order) {

        String oid = CommonUtils.uuid();

        order.setOid(oid);

        Date date = new Date();

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

        String format = dateFormat.format(date);

        order.setOrdertime(format);

        orderDao.addOrder(order);

        return order;

    }

    @Override
    public Order mapList(Map<String, CartItem> map, Order order) {

        List<OrderItem> orderItemList = new ArrayList<>();

        for (Map.Entry<String, CartItem> entry: map.entrySet()) {

            OrderItem orderItem = new OrderItem();

            String bid = entry.getKey();

            CartItem cartItem = entry.getValue();

            orderItem.setIid(CommonUtils.uuid());

            orderItem.setBid(bid);

            orderItem.setOid(order.getOid());

            orderItem.setCount(cartItem.getCount());

            double v = Double.parseDouble(cartItem.getBook().getPrice());

            double a = v * cartItem.getCount();

            orderItem.setSubtotal(a + "");

            // 新建book对象
            Book book = new Book();

            book.setBid(bid);

            Book load = bookService.load(book);

            // 将book加入 orderitem
            orderItem.setBook(load);

            orderItemList.add(orderItem);

            orderDao.addOrderitem(orderItem);
        }

        order.setOrderItemList(orderItemList);

        return order;

    }

    @Override
    public List<Order> orderListByUid(String uid) {

        System.out.println("开始遍历");

        List<Order> orderList = orderDao.findOrderByUid(uid);

        orderList = forOrder(orderList);

        System.out.println("遍历结束");

        return orderList;
    }



    @Override
    public Order load(String oid) {

        Order load = orderDao.load(oid);

        List<OrderItem> orderItemList = orderDao.findOrderItemByOid(oid);

        Book book = new Book();

        for (OrderItem orderItem : orderItemList) {

            book.setBid(orderItem.getBid());

            Book load1 = bookService.load(book);

            orderItem.setBook(load1);

        }

        load.setOrderItemList(orderItemList);

        return load;

    }

    @Override
    public void confirm(String oid) throws OrderException {

        String state = orderDao.getStateByOid(oid);

        if(state.equalsIgnoreCase("2")){

            orderDao.updateState(oid, "3");

        }else {

            throw new OrderException("您当前的状态不允许这样操作!");

        }

    }

    @Override
    public void payConfirm(String oid, String address) throws OrderException {

        String state = orderDao.getStateByOid(oid);

        if(state.equalsIgnoreCase("0")){

            orderDao.updateState(oid, "1");

            orderDao.updateAddress(oid, address);

        }else {

            throw new OrderException("您当前的状态不允许这样操作!");

        }

    }

    @Override
    public void sendConfirm(String oid) throws OrderException {

        String state = orderDao.getStateByOid(oid);

        if(state.equalsIgnoreCase("1")){

            orderDao.updateState(oid, "2");

        }else {

            throw new OrderException("您当前的状态不允许这样操作!");

        }
    }

    @Override
    public void balancePay(String oid, String uid) throws OrderException {

        Order load = orderDao.load(oid);

        double total = Double.parseDouble(load.getTotal());

        double balance = Double.parseDouble(userService.findBalance(uid));

        if(total > balance){

            throw new OrderException("账户余额不足,请更改支付方式或充值");

        }

        String newBalance = String.valueOf(formate(balance - total));

        userService.updateBalance(uid,newBalance);

    }

    @Override
    public List<Order> findAllOrder() {

        List<Order> orderAll = orderDao.findOrderAll();

        orderAll = forOrder(orderAll);

        return orderAll;

    }

    @Override
    public List<Order> findOrderByState(String state) {

        List<Order> orders = orderDao.findOrderByState(state);

        orders = forOrder(orders);

        return orders;

    }

    private List<Order> forOrder(List<Order> orderList) {

        for (Order order : orderList) {

            List<OrderItem> orderItemList = orderDao.findOrderItemByOid(order.getOid());

            for (OrderItem orderItem : orderItemList) {

                Book book = new Book();

                book.setBid(orderItem.getBid());

                Book load = bookService.load(book);

                orderItem.setBook(load);

            }

            order.setOrderItemList(orderItemList);
        }

        return orderList;

    }

    private double formate(double f){

        BigDecimal bigDecimal = new BigDecimal(f);

        double f1 = bigDecimal.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();

        return f1;

    }

}
