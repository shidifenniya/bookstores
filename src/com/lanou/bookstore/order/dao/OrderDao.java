package com.lanou.bookstore.order.dao;

import com.lanou.bookstore.order.domain.Order;
import com.lanou.bookstore.order.domain.OrderItem;

import java.util.List;

/**
 * Created by dllo on 17/9/22.
 */
public interface OrderDao {

    void addOrder(Order order);

    void addOrderitem(OrderItem orderItem);

    List<Order> findOrderByUid(String uid);

    List<Order> findOrderAll();

    List<Order> findOrderByState(String state);

    List<OrderItem> findOrderItemByOid(String oid);

    Order load(String oid);

    String getStateByOid(String oid);

    void updateState(String oid, String state);

    void updateAddress(String oid, String address);

}
