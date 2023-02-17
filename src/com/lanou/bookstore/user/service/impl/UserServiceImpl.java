package com.lanou.bookstore.user.service.impl;

import com.lanou.bookstore.user.dao.impl.UserDaoImpl;
import com.lanou.bookstore.user.domain.Balance;
import com.lanou.bookstore.user.domain.Recharge;
import com.lanou.bookstore.user.domain.User;
import com.lanou.bookstore.user.service.UserException;
import com.lanou.bookstore.user.service.UserService;
import com.lanou.commons.CommonUtils;
import com.lanou.mail.Mail;
import com.lanou.mail.MailUtils;

import javax.mail.MessagingException;
import javax.mail.Session;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by dllo on 17/9/21.
 */
public class UserServiceImpl implements UserService {

    //公共的dao对象

    private UserDaoImpl userDao = new UserDaoImpl();

    /*

    判断用户是否存在,不存在就添加,存在抛出错误信息

     */

    @Override
    public void register(User user) throws UserException {

        System.out.println("register");

        User dbUser = userDao.findWithUname(user.getUsername());

        if(dbUser != null){

            throw new UserException("用户:" + user.getUsername() + "已经存在");

        }

        dbUser = userDao.findWithEmail(user.getEmail());

        if(dbUser != null){

            throw new UserException(user.getEmail() + "这个邮箱已经被注册了");

        }

        user.setUid(CommonUtils.uuid());

        user.setCode(CommonUtils.uuid() + CommonUtils.uuid());

        userDao.addUser(user);

    }

    @Override
    public Map<String, String> checkout(User formUser) {

        Map<String,String> map = new HashMap<>();

        String username = formUser.getUsername();

        String password = formUser.getPassword();

        if(username.length() < 3 || username.length() > 15){

            if(username.equals("")){

                map.put("nameSize","用户名不能为空");

            }else {

                map.put("nameSize","用户名长度必须3-15之间");

            }

        }

        if(password.length() < 3 || password.length() > 15){

            if(password.equals("")){

                map.put("passwordSize","密码不能为空");

            } else {

                map.put("passwordSize","用户名长度必须3-15之间");

            }
        }

        return map;


    }

    @Override
    public Map<String, String> checkUsername(String username) {

        Map<String,String> map = new HashMap<>();

        if(username.length() < 3 || username.length() > 15){

            if(username.equals("")){

                map.put("nameSize","用户名不能为空");

            }else {

                map.put("nameSize","用户名长度必须3-15之间");

            }

            return map;

        }

        User withUname = userDao.findWithUname(username);

        if(withUname != null){

            map.put("nameSize","用户名已注册");

        }

        return map;

    }

    @Override
    public String setEmail(String email) throws IOException, MessagingException {

        User dbUser = userDao.findWithEmail(email);

        Session session = MailUtils.createSession("smtp.163.com","shidifenniya@163.com","HXTPXDZQKXLVBNCG");

        String from = "shidifenniya@163.com";

        System.out.println(email);

        String to = email;

        String subject = "激活邮箱";

        String content = "<a href='http://localhost:8080//UserServlet?method=activateEmail&code="+dbUser.getCode()+"'>" + "点此链接激活邮箱" + "</a>";

        String content2 = "<p>http://localhost:8080//UserServlet?method=activateEmail&code=" + dbUser.getCode() + "</p>";

        Mail mail = new Mail(from,to,subject,content + content2);

        MailUtils.send(session,mail);

        return dbUser.getCode();

    }

    @Override
    public boolean activiceEmail(String code) throws SQLException {

        int i = userDao.activate(code);

        if(i > 0){

            return true;

        }

        return false;

    }

    @Override
    public void login(User user) throws UserException {

        User dbUser = userDao.findWithUname(user.getUsername());

        if(dbUser == null){

            throw new UserException("用户:" + user.getUsername() + "不存在!");

        }

        dbUser = userDao.findWithPassword(user.getUsername(), user.getPassword());

        if(dbUser == null){

            throw new UserException("用户:" + user.getUsername() + "密码不匹配!");

        }

        dbUser = userDao.findWithState(dbUser.getUid());

        if(dbUser == null){

            throw new UserException("用户:" + user.getUsername() + "未激活,请前去邮箱激活!");

        }

    }

    public String findUid(String username){


        User user = userDao.findWithUname(username);

        return user.getUid();

    }

    @Override
    public String findUidByCode(String code) {

        User user = userDao.findWithCode(code);

        return user.getUid();
    }

    @Override
    public void setUpBalance(String uid) {

        userDao.setUpBalance(uid);

    }

    @Override
    public String findBalance(String uid) {

        Balance balance = userDao.findBalance(uid);

        return balance.getBalance();
    }

    @Override
    public String setRecharge(String rid, String uid) throws UserException {

        Balance balance = userDao.findBalance(uid);

        double b = Double.parseDouble(balance.getBalance());

//        System.out.println(b);

        Recharge card = userDao.findCard(rid);

        if(card == null){

            throw new UserException("该激活码无法使用");

        }

//        System.out.println(card.getBalance());

        b = b + Double.parseDouble(card.getBalance());

//        System.out.println(b);

//        System.out.println(String.valueOf(b));

        userDao.UpdateBalance(uid, String.valueOf(b));

        userDao.destoryCard(rid);

        return card.getBalance();

    }

    @Override
    public void updateBalance(String uid, String balance) {

        userDao.UpdateBalance(uid,balance);

    }

}
