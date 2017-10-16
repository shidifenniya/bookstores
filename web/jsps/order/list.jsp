<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>订单列表</title>

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

    <style>
        * {
            font-size: 11pt;
        }

        /*div {*/
            /*border: solid 2px gray;*/
            /*width: 75px;*/
            /*height: 75px;*/
            /*text-align: center;*/
        /*}*/

        /*li {*/
            /*margin: 10px;*/
        /*}*/

    </style>
</head>

<body>
<c:forEach items="${orders}" var="order">
<div class="panel panel-default">
    <div class="panel-heading">订单编号：${order.oid}</div>
    <div class="panel-body">
        <p>成交时间：${order.ordertime}</p>
        <p>金额：<span style="color: red">${order.total}</span></p>
        <c:choose>
            <c:when test="${order.state == '0'}">
                <a class="btn btn-primary btn-sm" href="<c:url value='/OrderServlet?method=load&oid=${order.oid}'/>">前去付款</a>
            </c:when>
            <c:when test="${order.state == '1'}">
                已付款,等待发货
            </c:when>
            <c:when test="${order.state == '2'}">
                <a class="btn btn-primary btn-sm" href="<c:url value='/OrderServlet?method=confirm&oid=${order.oid}'/>">确认收货</a>
            </c:when>
            <c:otherwise>
                完成交易
            </c:otherwise>
        </c:choose>
    </div>
    <table class="table">
    <c:forEach items="${order.orderItemList}" var="orderItem">
        <tr>
            <td width="15%">
                <div><img src="<c:url value='/${orderItem.book.image}'/>" height="75"/></div>
            </td>
            <td style="line-height: 75px">书名：${orderItem.book.bname}</td>
            <td style="line-height: 75px">单价：${orderItem.book.price}元</td>
            <td style="line-height: 75px">作者：${orderItem.book.author}</td>
            <td style="line-height: 75px">数量：${orderItem.count}</td>
            <td style="line-height: 75px">小计：${orderItem.subtotal}元</td>
        </tr>
    </c:forEach>
    </table>
</div>
</c:forEach>

<%--<table border="1" width="100%" cellspacing="0" background="black">--%>

    <%--<c:forEach items="${orders}" var="order">--%>

        <%--<tr bgcolor="gray" bordercolor="gray">--%>
            <%--<td colspan="6">--%>
                <%--订单编号：${order.oid}　成交时间：${order.ordertime}　金额：<font color="red"><b>${order.total}</b></font>　--%>

                <%--<c:choose>--%>
                    <%--<c:when test="${order.state == '0'}">--%>
                        <%--订单未付款<a href="<c:url value='/OrderServlet?method=load&oid=${order.oid}'/>">去付款</a>--%>
                    <%--</c:when>--%>
                    <%--<c:when test="${order.state == '1'}">--%>
                        <%--已付款,等待发货--%>
                    <%--</c:when>--%>
                    <%--<c:when test="${order.state == '2'}">--%>
                        <%--<a href="<c:url value='/OrderServlet?method=confirm&oid=${order.oid}'/>">确认收货</a>--%>
                    <%--</c:when>--%>
                    <%--<c:otherwise>--%>
                        <%--完成交易--%>
                    <%--</c:otherwise>--%>
                <%--</c:choose>--%>

            <%--</td>--%>
        <%--</tr>--%>
        <%--<c:forEach items="${order.orderItemList}" var="orderItem">--%>
            <%--<tr bordercolor="gray" align="center">--%>
                <%--<td width="15%">--%>
                    <%--<div><img src="<c:url value='/${orderItem.book.image}'/>" height="75"/></div>--%>
                <%--</td>--%>
                <%--<td>书名：${orderItem.book.bname}</td>--%>
                <%--<td>单价：${orderItem.book.price}元</td>--%>
                <%--<td>作者：${orderItem.book.author}</td>--%>
                <%--<td>数量：${orderItem.count}</td>--%>
                <%--<td>小计：${orderItem.subtotal}元</td>--%>
            <%--</tr>--%>
        <%--</c:forEach>--%>

    <%--</c:forEach>--%>

<%--</table>--%>
</body>
</html>
