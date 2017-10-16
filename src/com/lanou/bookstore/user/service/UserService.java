package com.lanou.bookstore.user.service;

import com.lanou.bookstore.user.domain.User;

import javax.mail.MessagingException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

/**
 * Created by dllo on 17/9/21.
 */
public interface UserService {

    void register(User user) throws UserException;

    Map<String,String> checkout(User formUser);

    Map<String,String> checkUsername(String username);

    String setEmail(String email) throws IOException, MessagingException;

    boolean activiceEmail(String code) throws SQLException;

    void login(User user) throws UserException;

    String findUid(String username);

    String findUidByCode(String code);

    void setUpBalance(String uid);

    String findBalance(String uid);

    String setRecharge(String rid, String uid) throws UserException;

    void updateBalance(String uid, String balance);

}
