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
    <style>
        body {
            margin: 50px 0;
            text-align: center;
            font-family: "PingFangSC-Regular", "Open Sans", Arial, "Hiragino Sans GB", "Microsoft YaHei", "STHeiti", "WenQuanYi Micro Hei", SimSun, sans-serif;
        }
        .inp {
            border: 1px solid #cccccc;
            border-radius: 2px;
            padding: 0 10px;
            width: 278px;
            height: 40px;
            font-size: 18px;
        }
        .btn {
            border: 1px solid #cccccc;
            border-radius: 2px;
            width: 100px;
            height: 40px;
            font-size: 16px;
            color: #666;
            cursor: pointer;
            background: white linear-gradient(180deg, #ffffff 0%, #f3f3f3 100%);
        }
        .btn:hover {
            background: white linear-gradient(0deg, #ffffff 0%, #f3f3f3 100%)
        }
        #captcha1,
        #captcha2 {
            width: 300px;
            display: inline-block;
        }
        .show {
            display: block;
        }
        .hide {
            display: none;
        }
        #notice1,
        #notice2 {
            color: red;
        }
        label {
            vertical-align: top;
            display: inline-block;
            width: 80px;
            text-align: right;
        }
        #wait1, #wait2 {
            text-align: left;
            color: #666;
            margin: 0;
        }
    </style>

    <link href="../../BS/css/bootstrap.min.css" rel="stylesheet">
    <script src="../../BS/js/jquery-3.2.1.js"></script>
    <script src="../../BS/js/bootstrap.min.js"></script>

    <%-- 极验 --%>
    <script src="../../gt.js"></script>

</head>

<body>


<div class="container">

    <form action="/UserServlet" method="post" class="form-signin">
        <input type="hidden" name="method" value="login"/>
        <h2 class="form-signin-heading">登录</h2>

        <label class="sr-only" for="inputUsername">用户名:</label>
        <input style="width: 300px;margin-left: 208px" type="text" name="username" value="" id="inputUsername" class="form-control" placeholder="Username"
               required autofocus><br/>

        <label for="inputPassword" class="sr-only">密　码：</label>
        <input style="width: 300px;margin-left: 208px" type="password" name="password" id="inputPassword" class="form-control" placeholder="Password"
               required><br/>

        <label class="sr-only">完成验证：</label>
        <div id="captcha1">
            <%--<p id="wait1" class="show">正在加载验证码......</p>--%>
        </div>

        <p id="notice1" class="hide">请先完成验证</p>

        <br><br>

        <c:if test="${not empty msg}">
            <div style="width: 300px;margin-left: 208px"><div class="alert alert-danger" role="alert"><span class="glyphicon glyphicon-remove"></span>${msg}</div></div>
        </c:if>

        <button style="width: 300px;margin-left: 208px" class="btn btn-lg btn-primary btn-block" id="submit1" type="submit">登录</button>

    </form>
</div>
</body>

<script>

    var handler1 = function (captchaObj) {
        $("#submit1").click(function (e) {
            var result = captchaObj.getValidate();
            if (!result) {

                $("#notice1").show();

                setTimeout(function () {

                    $("#notice1").hide();
                }, 2000);

                e.preventDefault();
            }
        });
        // 将验证码加到id为captcha的元素里，同时会有三个input的值用于表单提交
        captchaObj.appendTo("#captcha1");
        captchaObj.onReady(function () {
            $("#wait1").hide();
        });
        // 更多接口参考：http://www.geetest.com/install/sections/idx-client-sdk.html
    };
    $.ajax({
        url: "/startCaptcha?t=" + (new Date()).getTime(), // 加随机数防止缓存
        type: "get",
        dataType: "json",
        success: function (data) {
            // 调用 initGeetest 初始化参数
            // 参数1：配置参数
            // 参数2：回调，回调的第一个参数验证码对象，之后可以使用它调用相应的接口
            initGeetest({
                gt: data.gt,
                challenge: data.challenge,
                new_captcha: data.new_captcha, // 用于宕机时表示是新验证码的宕机
                offline: !data.success, // 表示用户后台检测极验服务器是否宕机，一般不需要关注
                product: "float", // 产品形式，包括：float，popup
                width: "100%",
                lang: 'en'
                // 更多配置参数请参见：http://www.geetest.com/install/sections/idx-client-sdk.html#config
            }, handler1);
        }
    });
</script>

</html>
