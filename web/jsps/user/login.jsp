<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

    <title>登录</title>
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
</head>

<body>


<div class="container">

    <form action="/UserServlet" method="post" class="form-signin">

        <input type="hidden" name="method" value="login"/>
        <h2 class="form-signin-heading">登录</h2>


        <label class="sr-only" for="inputUsername">用户名:</label>
        <input type="text" name="username" value="" id="inputUsername" class="form-control" placeholder="Username"
               required autofocus><br/>

        <label for="inputPassword" class="sr-only">密　码：</label>
        <input type="password" name="password" id="inputPassword" class="form-control" placeholder="Password"
               required><br/>

        <c:if test="${not empty msg}">
            <label class="alert alert-danger" role="alert"><span class="glyphicon glyphicon-remove"></span>${msg}</label>
        </c:if>
        <button class="btn btn-lg btn-primary btn-block" type="submit">登录</button>

    </form>
</div>
</body>
</html>
