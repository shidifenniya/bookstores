package com.lanou.bookstore.user.dao.impl;

import com.lanou.bookstore.user.dao.UserDao;
import com.lanou.bookstore.user.domain.Balance;
import com.lanou.bookstore.user.domain.Recharge;
import com.lanou.bookstore.user.domain.User;
import com.lanou.jdbc.GxQueryRunner;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;


import java.sql.SQLException;


/**
 * Created by dllo on 17/9/21.
 */
public class UserDaoImpl implements UserDao {

    // 公共qr

    private QueryRunner qr = new GxQueryRunner();

    @Override
    public User findWithUname(String username) {

        String str = "select * from tb_user where username=?";

        try {

            return qr.query(str, new BeanHandler<>(User.class), username);

        } catch (SQLException e) {

            throw new RuntimeException(e);

        }

    }

    @Override
    public User findWithEmail(String email) {
        String str = "select * from tb_user where email=?";

        try {

            return qr.query(str, new BeanHandler<>(User.class), email);

        } catch (SQLException e) {

            throw new RuntimeException(e);

        }
    }

    @Override
    public User findWithPassword(String username, String password) {

        String str = "select * from tb_user where username=? and password=?";

        try {

            return qr.query(str,new BeanHandler<>(User.class), username, password);

        } catch (SQLException e) {

            throw new RuntimeException(e);

        }

    }

    @Override
    public User findWithState(String uid) {

        String str = "select * from tb_user where uid=? and state=?";

        try {

            return qr.query(str, new BeanHandler<>(User.class), uid, true);

        } catch (SQLException e) {

            throw new RuntimeException(e);

        }

    }

    @Override
    public User findWithCode(String code) {

        String str = "select * from tb_user where code=?";

        try {

            return qr.query(str, new BeanHandler<>(User.class), code);

        } catch (SQLException e) {

            throw new RuntimeException(e);

        }

    }

    @Override
    public Balance findBalance(String uid) {

        String str = "select * from balance where uid=?";

        try {

            return qr.query(str, new BeanHandler<>(Balance.class), uid);

        } catch (SQLException e) {

            throw new RuntimeException(e);

        }

    }

    @Override
    public Recharge findCard(String rid) {

        String str = "select * from rechargeCard where rid=? and state=?";

        try {

            return qr.query(str, new BeanHandler<>(Recharge.class), rid, false);

        } catch (SQLException e) {

            throw new RuntimeException(e);

        }
    }

    @Override
    public void destoryCard(String rid) {

        String str = "update rechargeCard set state=? where rid=?";

        try {

            qr.update(str, true, rid);

        } catch (SQLException e) {

            throw new RuntimeException(e);

        }

    }

    @Override
    public void addUser(User user) {

        String str = "insert into tb_user(uid,username,password,email,code,state) values(?,?,?,?,?,?)";

        // 插入信息
        Object[] obj = {user.getUid(),user.getUsername(),user.getPassword(),user.getEmail(),user.getCode(),false};

        try {

            // qr插入
            qr.update(str,obj);

        } catch (SQLException e) {

            //抛出异常
            throw new RuntimeException(e);

        }

        System.out.println("添加完成!");

    }

    @Override
    public void setUpBalance(String uid) {

        String str = "insert into balance(uid,balance) values(?,?)";

        try {

            qr.update(str,uid,"0");

        } catch (SQLException e) {

            throw new RuntimeException(e);

        }

    }

    @Override
    public void UpdateBalance(String uid, String balance) {

        String str = "update balance set balance=? where uid=?";

        try {

            qr.update(str,balance,uid);

        } catch (SQLException e) {

            throw new RuntimeException(e);

        }

    }

    @Override
    public int activate(String code) throws SQLException {

        String str = "update tb_user set state=? where code=?";

        Object[] obj = {true, code};

        return qr.update(str,obj);
    }

}
