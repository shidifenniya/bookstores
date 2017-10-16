<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

    <title>注册</title>

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
</head>
<link href="../../BS/css/bootstrap.min.css" rel="stylesheet">
<script src="../../BS/js/jquery-3.2.1.js"></script>
<script src="../../BS/js/bootstrap.min.js"></script>
<body>

<div class="container">

    <form action="/UserServlet" method="post" class="form-signin">

        <input type="hidden" name="method" value="regist"/>

        <h2 class="form-signin-heading">加入 Next Door</h2>
        <div class="form-group">

            <label>用户名：</label>

            <input class="form-control" placeholder="Username" id="username1" type="text" name="username"
                   value="${user.username}"/>
        </div>
        <c:if test="${not empty map.nameSize}">
            <label class="alert alert-danger" role="alert" id="nameSize">${map.nameSize}</label>
        </c:if>

        <div class="form-group">

            <label>密　码：</label>
            <input class="form-control" type="password" name="password" placeholder="Password"/>
        </div>
        <c:if test="${not empty map.passwordSize}">
            <label class="alert alert-danger" role="alert">${map.passwordSize}</label>
        </c:if>

        <div class="form-group">
            <label>邮　箱：</label>
            <input class="form-control" type="text" name="email" value="${user.email}" placeholder="Email Address">
        </div>

        <c:if test="${not empty msg}">
            <label class="alert alert-danger" role="alert"><span class="glyphicon glyphicon-remove"></span>${msg}</label>
        </c:if>

        <button class="btn btn-lg btn-primary btn-block" type="submit">注册</button>

    </form>

</div>

<%--<p style="color: red; font-weight: 900">${msg }</p>--%>
<%--<form action="/UserServlet" method="post">--%>


    <%--密　码：<span style="color: red">${map.passwordSize}</span><br/>--%>
    <%--邮　箱：<input type="text" name="email" value="${user.email}"/><br/>--%>
    <%--<input type="submit" value="注册"/>--%>
<%--</form>--%>
</body>

<script>

    $("#username1").blur(function () {

//		alert("1");

        $.post("/UsernameServlet", {username: $("#username1").val()}, function (nameCheck) {

//			alert(nameCheck);

            if (nameCheck.nameNum == "0") {

                $("#nameSize").css("color", "red");

                $("#nameSize").html(nameCheck.nameSize);

            } else {

                $("#nameSize").css("color", "green");

                $("#nameSize").html(nameCheck.nameSize);

            }

        }, "json")

    })

</script>
</html>
