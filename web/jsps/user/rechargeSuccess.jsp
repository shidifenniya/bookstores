<%--
  Created by IntelliJ IDEA.
  User: dllo
  Date: 17/9/26
  Time: 上午10:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>成功</title>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="refresh" content="5;url=jsps/user/msg.jsp">

    <link href="../../BS/css/bootstrap.min.css" rel="stylesheet">
    <script src="../../BS/js/jquery-3.2.1.js"></script>
    <script src="../../BS/js/bootstrap.min.js"></script>
</head>
<body onload="showMun()">

<div class="jumbotron text-center">
    <h3>${msg}</h3>
    <p id="time"></p>
    <p><a class="btn btn-primary btn-lg" href="jsps/user/msg.jsp" role="button">直接跳转</a></p>
</div>

</body>

<script>

    var i = 6;

    function showMun() {

        i = i - 1;

        document.getElementById("time").innerHTML = "还有" + i + "秒跳转页面";

        setTimeout('showMun()',1000);

    }

</script>
</html>
