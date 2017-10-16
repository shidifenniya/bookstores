<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>top</title>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    <meta http-equiv="content-type" content="text/html;charset=utf-8">
    <!--
    <link rel="stylesheet" type="text/css" href="styles.css">
    -->
    <link href="../BS/css/bootstrap.min.css" rel="stylesheet">
    <script src="../BS/js/jquery-3.2.1.js"></script>
    <script src="../BS/js/bootstrap.min.js"></script>
    <style type="text/css">
        /*body {*/

        /*color: #b2dba1;*/

        /*font-family: ".SF NS Text";*/
        /*}*/

        /*a {*/
        /*text-transform: none;*/
        /*text-decoration: none;*/
        /*}*/

        a:hover {

            text-decoration: underline;

        }
    </style>
</head>

<body>

<div class="jumbotron text-center">

    <h1>Next Door Bookstore</h1>


    <c:choose>

    <c:when test="${not empty username}">

        <p style="height: 45px"></p>

    </c:when>

    <c:otherwise>

        <p>
            <a class="btn btn-primary btn-lg" href="<c:url value='/jsps/user/login.jsp'/>" target="body" role="button">Login</a>
            <a class="btn btn-primary btn-lg" href="<c:url value='/jsps/user/regist.jsp'/>" target="body" role="button">Sign</a>
        </p>

    </c:otherwise>

    </c:choose>

<c:if test="${not empty username}">
    <nav class="navbar navbar-default">
        <div class="container-fluid">
            <div class="navbar-header">
                <a class="navbar-brand" href="#">
                ${username}</a>
            </div>
            <div class="collapse navbar-collapse">
                <ul class="nav navbar-nav">
                    <li>
                        <a href="<c:url value='/OrderServlet?method=myCart'/>" target="body">
                            <span class="glyphicon glyphicon-shopping-cart"></span>
                            购物车</a>
                    </li>
                    <li>
                        <a href="<c:url value='/OrderServlet?method=myOrders'/>" target="body">
                            <span class="glyphicon glyphicon-th-list"></span>
                            订单管理
                        </a>
                    </li>
                    <li>
                        <a href="<c:url value='/jsps/user/recharge.jsp'/>" target="body">
                            <span class="glyphicon glyphicon-flash"></span>
                            账户充值
                        </a>
                    </li>
                    <li>
                        <a href="/UserServlet?method=quit" target="body">
                            <span class="glyphicon glyphicon-log-out"></span>
                            退出</a>
                    </li>
                </ul>
                <div class="nav navbar-nav navbar-right">
                    <p class="navbar-text" style="font-size: 12px">
                        账户余额 : ${balance} <span class="glyphicon glyphicon-usd"></span>
                    </p>
                </div>
            </div>
        </div>
    </nav>
</c:if>
</div>


<%--<div style="font-size: 10pt;margin-top: -4%">--%>
<%--您好：--%>
<%--<c:choose>--%>

<%--<c:when test="${not empty username}">--%>

<%--${username}--%>

<%--</c:when>--%>

<%--<c:otherwise>--%>

<%--请登录--%>

<%--</c:otherwise>--%>

<%--</c:choose>--%>

<%--&nbsp;&nbsp;|&nbsp;&nbsp;--%>
<%--<a href="<c:url value='/OrderServlet?method=myCart'/>" target="body">我的购物车</a>&nbsp;&nbsp;|&nbsp;&nbsp;--%>
<%--<a href="<c:url value='/OrderServlet?method=myOrders'/>" target="body">我的订单</a>&nbsp;&nbsp;|&nbsp;&nbsp;--%>
<%--<a href="/UserServlet?method=quit" target="body">退出</a>--%>
<%--<br/>--%>

<%--<c:choose>--%>
<%--<c:when test="${empty username}">--%>
<%--<a href="<c:url value='/jsps/user/login.jsp'/>" target="body">登录</a> |&nbsp;--%>
<%--<a href="<c:url value='/jsps/user/regist.jsp'/>" target="body">注册</a>--%>
<%--</c:when>--%>
<%--<c:otherwise>--%>
<%--账户余额:&nbsp;${balance} &nbsp; | &nbsp;--%>
<%--<a href= "<c:url value='/jsps/user/recharge.jsp'/>" target="body">充值</a>--%>
<%--</c:otherwise>--%>
<%--</c:choose>--%>
<%--</div>--%>
</body>
</html>
