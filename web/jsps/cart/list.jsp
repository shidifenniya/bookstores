<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ page import="com.lanou.bookstore.user.domain.User" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>购物车列表</title>

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
    <link href="../../BS/css/bootstrap.min.css" rel="stylesheet">
    <script src="../../BS/js/jquery-3.2.1.js"></script>
    <script src="../../BS/js/bootstrap.min.js"></script>
    <style type="text/css">
        * {
            font-size: 11pt;
        }

        div {
            margin: 20px;
            border: solid 2px gray;
            width: 150px;
            height: 150px;
            text-align: center;
        }

        li {
            margin: 10px;
        }


        <%--#buy {--%>
            <%--background: url(<c:url value='/images/all.png'/>) no-repeat;--%>
            <%--display: inline-block;--%>

            <%--background-position: 0 -902px;--%>
            <%--margin-left: 30px;--%>
            <%--height: 36px;--%>
            <%--width: 146px;--%>
        <%--}--%>

        <%--#buy:HOVER {--%>
            <%--background: url(<c:url value='/images/all.png'/>) no-repeat;--%>
            <%--display: inline-block;--%>

            <%--background-position: 0 -938px;--%>
            <%--margin-left: 30px;--%>
            <%--height: 36px;--%>
            <%--width: 146px;--%>
        <%--}--%>
    </style>
</head>

<body>
<table class="table table-striped">

    <tr>
        <td colspan="7" align="right" style="font-size: 15pt; font-weight: 900">
            <a href="/CartServlet?method=clear" class="btn btn-default btn-primary">清空购物车</a>
        </td>
    </tr>

    <tr>
        <th>图片</th>
        <th>书名</th>
        <th>作者</th>
        <th>单价</th>
        <th>数量</th>
        <th>小计</th>
        <th>操作</th>
    </tr>

    <c:set var="username" value="${username}"/>

    <c:forEach items="${sessionScope[username].map}" var="cartItems">
        <tr>
            <td>
                <div><img src="<c:url value='/${cartItems.value.book.image}'/>"/></div>
            </td>
            <td style="line-height:200px;">${cartItems.value.book.bname}</td>
            <td style="line-height:200px;">${cartItems.value.book.author}</td>
            <td style="line-height:200px;">${cartItems.value.book.price}元</td>
            <td style="line-height:200px;">${cartItems.value.count}</td>
            <td style="line-height:200px;">

                <fmt:formatNumber type="number" value="${cartItems.value.book.price * cartItems.value.count}" maxFractionDigits="2"/>

            元</td>
            <td style="line-height:200px;"><a href="/CartServlet?method=delete&bid=${cartItems.value.book.bid}"
            class="btn btn-warning btn-default" style="margin-top: 80px"
            >删除</a></td>
        </tr>
    </c:forEach>

    <tr>
        <td colspan="7" align="right" style="font-size: 15pt; font-weight: 900">
            <input type="hidden"

            <c:set var="sum" value="0"/>
            <c:forEach items="${sessionScope[username].map}" var="cartItems">
                <c:set var="sum" value="${sum + cartItems.value.book.price * cartItems.value.count}"/>
            </c:forEach>

            >
            <p><span class="glyphicon glyphicon-yen"></span>

                &nbsp;&nbsp;

                <fmt:formatNumber type="number" value="${sum}" maxFractionDigits="2"/>

            </p>
        </td>
    </tr>
    <tr>
        <td colspan="7" align="right" style="font-size: 15pt; font-weight: 900">
            <a class="btn btn-primary btn-lg" href="<c:url value='/OrderServlet?method=add&sum=${sum}'/>">一键购买</a>
        </td>
    </tr>

</table>

<%--<table border="1" width="100%" cellspacing="0" background="black">--%>
    <%--<tr>--%>
        <%--<td colspan="7" align="right" style="font-size: 15pt; font-weight: 900">--%>
            <%--<a href="/CartServlet?method=clear">清空购物车</a>--%>
        <%--</td>--%>
    <%--</tr>--%>
    <%--<tr>--%>
        <%--<th>图片</th>--%>
        <%--<th>书名</th>--%>
        <%--<th>作者</th>--%>
        <%--<th>单价</th>--%>
        <%--<th>数量</th>--%>
        <%--<th>小计</th>--%>
        <%--<th>操作</th>--%>
    <%--</tr>--%>

    <%--<c:set var="username" value="${username}"/>--%>

    <%--<c:forEach items="${sessionScope[username].map}" var="cartItems">--%>
        <%--<tr>--%>
            <%--<td>--%>
                <%--<div><img src="<c:url value='/${cartItems.value.book.image}'/>"/></div>--%>
            <%--</td>--%>
            <%--<td>${cartItems.value.book.bname}</td>--%>
            <%--<td>${cartItems.value.book.author}</td>--%>
            <%--<td>${cartItems.value.book.price}元</td>--%>
            <%--<td>${cartItems.value.count}</td>--%>
            <%--<td>${cartItems.value.book.price * cartItems.value.count}元</td>--%>
            <%--<td><a href="/CartServlet?method=delete&bid=${cartItems.value.book.bid}">删除</a></td>--%>
        <%--</tr>--%>
    <%--</c:forEach>--%>

    <%--<tr>--%>
        <%--<td colspan="7" align="right" style="font-size: 15pt; font-weight: 900">--%>
            <%--<input type="hidden"--%>

            <%--<c:set var="sum" value="0"/>--%>
            <%--<c:forEach items="${sessionScope[username].map}" var="cartItems">--%>
                <%--<c:set var="sum" value="${sum + cartItems.value.book.price * cartItems.value.count}"/>--%>
            <%--</c:forEach>--%>

            <%-->--%>

            <%--合计：${sum}元--%>
        <%--</td>--%>
    <%--</tr>--%>
    <%--<tr>--%>
        <%--<td colspan="7" align="right" style="font-size: 15pt; font-weight: 900">--%>
            <%--<a id="buy" href="<c:url value='/OrderServlet?method=add&sum=${sum}'/>"></a>--%>
        <%--</td>--%>
    <%--</tr>--%>

<%--</table>--%>
</body>
</html>
