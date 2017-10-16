package com.lanou.bookstore.order.dao.impl;

import com.lanou.bookstore.order.dao.OrderDao;
import com.lanou.bookstore.order.domain.Order;
import com.lanou.bookstore.order.domain.OrderItem;
import com.lanou.jdbc.GxQueryRunner;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by dllo on 17/9/22.
 */
public class OrderDaoImpl implements OrderDao {

    private QueryRunner qr = new GxQueryRunner();

    @Override
    public void addOrder(Order order) {

        String str = "insert into orders (oid,ordertime,total,state,uid,address) values(?,?,?,?,?,?)";

        Object[] obj = {order.getOid(), order.getOrdertime(), order.getTotal(), 0, order.getUid(), order.getAddress()};

        try {

            qr.update(str, obj);

        } catch (SQLException e) {

            throw new RuntimeException(e);

        }

    }

    public void addOrderitem(OrderItem orderItem) {

        String str = "insert into orderitem (iid,count,subtotal,oid,bid) values(?,?,?,?,?)";

        Object[] obj = {orderItem.getIid(), orderItem.getCount(), orderItem.getSubtotal(), orderItem.getOid(), orderItem.getBid()};

        try {

            qr.update(str, obj);

        } catch (SQLException e) {

            throw new RuntimeException(e);

        }

    }

    @Override
    public List<Order> findOrderByUid(String uid) {

        String str = "select *from orders where uid=?";

        try {

            return qr.query(str, new BeanListHandler<>(Order.class), uid);

        } catch (SQLException e) {

            throw new RuntimeException(e);

        }

    }

    @Override
    public List<Order> findOrderAll() {

        String str = "select * from orders";

        try {

            return qr.query(str, new BeanListHandler<>(Order.class));

        } catch (SQLException e) {

            throw new RuntimeException(e);

        }
    }

    @Override
    public List<Order> findOrderByState(String state) {

        String str = "select * from orders where state=?";

        try {

            return qr.query(str, new BeanListHandler<>(Order.class),state);

        } catch (SQLException e) {

            throw new RuntimeException(e);

        }
    }

    @Override
    public List<OrderItem> findOrderItemByOid(String oid) {
        String str = "select *from orderitem where oid=?";

        try {

            return qr.query(str, new BeanListHandler<>(OrderItem.class), oid);

        } catch (SQLException e) {

            throw new RuntimeException(e);

        }
    }

    @Override
    public Order load(String oid) {

        String str = "select *from orders where oid=?";

        try {

            return qr.query(str, new BeanHandler<>(Order.class), oid);

        } catch (SQLException e) {

            throw new RuntimeException(e);

        }

    }

    @Override
    public String getStateByOid(String oid) {

        String str = "select state from orders where oid=?";

        try {

            Order order = qr.query(str, new BeanHandler<>(Order.class), oid);

            return order.getState();

        } catch (SQLException e) {

            throw new RuntimeException(e);

        }

    }

    @Override
    public void updateState(String oid, String state) {

        String str = "update orders set state=? where oid=?";

        try {

            qr.update(str, state, oid);

        } catch (SQLException e) {

            throw new RuntimeException(e);

        }

    }

    @Override
    public void updateAddress(String oid, String address) {

        String str = "update orders set address=? where oid=?";

        try {

            qr.update(str, address, oid);

        } catch (SQLException e) {

            throw new RuntimeException(e);

        }


    }

}
