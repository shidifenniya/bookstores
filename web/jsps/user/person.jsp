<%--
  Created by IntelliJ IDEA.
  User: dllo
  Date: 17/9/28
  Time: 下午4:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>person</title>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="../../BS/css/bootstrap.min.css" rel="stylesheet">
    <script src="../../BS/js/jquery-3.2.1.js"></script>
    <script src="../../BS/js/bootstrap.min.js"></script>
</head>

<body>

<div class="panel panel-default">
    <div class="panel-body">
        <div class="jumbotron">
            <h4>您好: ${username}</h4>
            <h3>余额:</h3>
            <p><a class="btn btn-primary btn-lg" href="#" role="button">Learn more</a></p>
        </div>
    </div>
    <div class="panel-footer">
        <button type="button" class="btn btn-default btn-lg">
            <span class="glyphicon glyphicon-log-out" aria-hidden="true"></span>
            退出
        </button>
    </div>
</div>

</body>
</html>
