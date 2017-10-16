<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>图书详细</title>

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
        body {
            font-size: 10pt;
        }

        /*div {*/
        /*!*margin: 20px;*!*/
        /*!*border: solid 2px gray;*!*/
        /*!*width: 150px;*!*/
        /*!*height: 150px;*!*/
        /*text-align: center;*/
        /*}*/

        li {
            margin: 10px;
        }

        <%--a {--%>
        <%--background: url(<c:url value='/images/all.png'/>) no-repeat;--%>
        <%--display: inline-block;--%>

        <%--background-position: 0 -70px;--%>
        <%--margin-left: 30px;--%>
        <%--height: 36px;--%>
        <%--width: 146px;--%>
        <%--}--%>

        <%--a:HOVER {--%>
        <%--background: url(<c:url value='/images/all.png'/>) no-repeat;--%>
        <%--display: inline-block;--%>

        <%--background-position: 0 -106px;--%>
        <%--margin-left: 30px;--%>
        <%--height: 36px;--%>
        <%--width: 146px;--%>
        <%--}--%>

        tr,td{

            text-align: center;

        }
    </style>
</head>

<body class="col-md-4 col-md-push-4">
<div class="panel panel-default" align="center">
    <div class="page-header">${book.bname}</div>
    <div class="panel-body">
        <img src="<c:url value='/${book.image}'/>" border="0"/>
    </div>

    <table class="table">
        <tr>
            <td>书名：${book.bname}</td>
        </tr>
        <tr>
            <td>作者：${book.author}</td>
        </tr>
        <tr>
            <td>单价：${book.price}</td>
        </tr>

        <tr>
            <td>
                <form class="form-inline" id="form" action="<c:url value='/CartServlet?method=add&bid=${book.bid}'/>"
                      method="post">

                    <div class="input-group">
                        <div class="input-group-addon">数量</div>
                        <input type="text" class="form-control" name="count" placeholder="count" value="1"/>
                        <div class="input-group-addon">本</div>
                    </div>

                    <c:choose>

                        <c:when test="${not empty username}">

                            <button type="submit" class="btn btn-primary btn-lg">
                                <span class="glyphicon glyphicon-shopping-cart"aria-hidden="true"></span>
                                加入购物车
                            </button>

                        </c:when>

                    </c:choose>

                </form>
            </td>
        </tr>
    </table>
</div>
</body>
</html>
