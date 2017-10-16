package com.lanou.bookstore.user.web.controller;

import com.lanou.bookstore.cart.domain.Cart;
import com.lanou.bookstore.cart.domain.CartItem;
import com.lanou.bookstore.user.domain.User;
import com.lanou.bookstore.user.service.UserException;
import com.lanou.bookstore.user.service.UserService;
import com.lanou.bookstore.user.service.impl.UserServiceImpl;
import com.lanou.commons.CommonUtils;
import com.lanou.mail.Mail;
import com.lanou.mail.MailUtils;
import com.lanou.servlet.BaseServlet;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by dllo on 17/9/21.
 */
@WebServlet("/UserServlet")
public class UserServlet extends BaseServlet {

    // 定义公共的UserService对象

    private UserServiceImpl userService = new UserServiceImpl();

    public String regist(HttpServletRequest request, HttpServletResponse response) {

        User user = CommonUtils.toBean(request.getParameterMap(), User.class);

        // 检查信息是否合法,返回错误信息
        Map<String, String> map = userService.checkout(user);

        request.setAttribute("user", user);

        if (!map.isEmpty()) {

            request.setAttribute("msg", "注册失败,请核对信息后再次注册");

            // 返回错误信息,map封装
            request.setAttribute("map", map);

            return "f:jsps/user/regist.jsp";

        }

        try {

            userService.register(user);

            String code = userService.setEmail(user.getEmail());

            request.setAttribute("msg", ",您已注册成功,请前去邮箱完成账户激活");

            request.getSession().setAttribute("jsp", "main");

            return "f:jsps/msg.jsp";

        } catch (UserException e) {

            request.setAttribute("msg", e.getMessage());

            return "f:jsps/user/regist.jsp";

        } catch (MessagingException e) {

            request.setAttribute("msg", "邮件发送失败!");

            return "f:jsps/user/regist.jsp";

        } catch (IOException e) {

            request.setAttribute("msg", "IO异常!");

            return "f:jsps/user/regist.jsp";

        }

    }

    public String activateEmail(HttpServletRequest request, HttpServletResponse response) throws SQLException {

        User user = CommonUtils.toBean(request.getParameterMap(), User.class);

        // 取出 code

        boolean b = userService.activiceEmail(user.getCode());

        if (b) {

            String uid = userService.findUidByCode(user.getCode());

            userService.setUpBalance(uid);

            request.setAttribute("msg", "恭喜您激活成功");

            request.getSession().setAttribute("jsp", "main");

            return "f:jsps/msg.jsp";

        } else {

            request.setAttribute("msg", "激活失败!");

            request.getSession().setAttribute("jsp", "main");

            return "f:jsps/msg.jsp";

        }

    }

    /**
     * 登录认证
     *
     * @param request
     * @param response
     * @return
     */

    public String login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = CommonUtils.toBean(request.getParameterMap(), User.class);

        try {

            userService.login(user);

        } catch (UserException e) {

            //记录设定的异常
            request.setAttribute("msg", e.getMessage());

            return "f:jsps/user/login.jsp";

        }


        request.getSession(true).setAttribute("username", user.getUsername());

        String balance = userService.findBalance(userService.findUid(user.getUsername()));

        request.getSession(true).setAttribute("balance", balance);

        if (request.getSession().getAttribute(user.getUsername()) == null) {

            Cart cart = new Cart();

            cart.setMap(new HashMap<>());

            request.getSession(true).setAttribute(user.getUsername(), cart);

        }

        return "r:jsps/user/msg.jsp";

    }

    /**
     * 退出登录
     * @param request
     * @param response
     * @return
     */

    public String quit(HttpServletRequest request, HttpServletResponse response) {

        if (request.getSession().getAttribute("username") == null) {

            request.getSession().setAttribute("jsp", "main");

            request.setAttribute("msg", "当前不允许进行这样的操作!");

            return "f:jsps/msg.jsp";

        }


        request.getSession().removeAttribute("username");

        return "r:jsps/user/msg.jsp";

    }

    /**
     * 充值卡充值
     * @param request
     * @param response
     * @return
     */

    public String recharge(HttpServletRequest request, HttpServletResponse response){

        // 表单提取卡密
        String rid = request.getParameter("rechargeCard");

        // session 中获取用户名
        String username = (String) request.getSession().getAttribute("username");

        String uid = userService.findUid(username);

        try {

            String recharge = userService.setRecharge(rid, uid);

            String balance = userService.findBalance(uid);

            // 重新存入 session 中
            request.getSession(true).setAttribute("balance", balance);

            request.setAttribute("msg","恭喜您"+username+",充值"+recharge+"元成功!");

            return "f:jsps/user/rechargeSuccess.jsp";

        } catch (UserException e) {

            request.setAttribute("msg",e.getMessage());

            return "f:jsps/user/recharge.jsp";

        }

    }
}