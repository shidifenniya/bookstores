package com.lanou.bookstore.order.service;


import com.lanou.bookstore.cart.domain.CartItem;
import com.lanou.bookstore.order.domain.Order;


import java.util.List;
import java.util.Map;

/**
 * Created by dllo on 17/9/22.
 */
public interface OrderService {

    Order add(Order order);

    Order mapList(Map<String, CartItem> map, Order order);

    List<Order> orderListByUid(String uid);

    Order load(String oid);

    void confirm(String oid) throws OrderException;

    void payConfirm(String oid, String address) throws OrderException;

    void sendConfirm(String oid) throws OrderException;

    void balancePay(String oid, String uid) throws OrderException;

    List<Order> findAllOrder();

    List<Order> findOrderByState(String state);
}
