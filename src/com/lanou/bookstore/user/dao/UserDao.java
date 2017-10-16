package com.lanou.bookstore.user.dao;

import com.lanou.bookstore.user.domain.Balance;
import com.lanou.bookstore.user.domain.Recharge;
import com.lanou.bookstore.user.domain.User;

import java.sql.SQLException;

/**
 * Created by dllo on 17/9/21.
 */
public interface UserDao {

    User findWithUname(String username);

    User findWithEmail(String email);

    User findWithPassword(String username, String  password);

    User findWithState(String uid);

    User findWithCode(String code);

    Balance findBalance(String uid);

    Recharge findCard(String rid);

    void destoryCard(String rid);

    void addUser(User user);

    void setUpBalance(String uid);

    void UpdateBalance(String uid, String balance);

    int activate(String code) throws SQLException;

}
