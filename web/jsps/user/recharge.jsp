<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: dllo
  Date: 17/9/26
  Time: 上午8:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>点卡充值</title>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="../../BS/css/bootstrap.min.css" rel="stylesheet">
    <script src="../../BS/js/jquery-3.2.1.js"></script>
    <script src="../../BS/js/bootstrap.min.js"></script>

    <%--<style type="text/css">--%>

    <%--body{--%>
    <%----%>
    <%--background-image: url("../../images/chongzhi.jpg");--%>

    <%--color: aliceblue;--%>

    <%--font-family: ".SF NS Text";--%>

    <%--}--%>

    <%--form{--%>

    <%--margin-top: 10%;--%>

    <%--margin-left: 5%;--%>
    <%--}--%>

    <%--p{--%>
    <%--margin-top: 10%;--%>

    <%--font-size: 5px;--%>

    <%--}--%>

    <%--</style>--%>

</head>


<body>

<form action="/UserServlet" method="post">
    <input type="hidden" name="method" value="recharge">
    <div class="form-group">
        <label for="rechargeCard">序列号</label>
        <div class="input-group">
            <span class="input-group-addon"><span class="glyphicon glyphicon-credit-card"></span></span>
            <input type="text" class="form-control" id="rechargeCard" placeholder="Card-Key" name="rechargeCard">
        </div>
        <p></p>
        <c:if test="${not empty msg}">
            <label class="alert alert-danger" role="alert"><span class="glyphicon glyphicon-remove"></span>&nbsp;${msg}
            </label>
        </c:if>
    </div>
    <div align="right">
        <button class="btn btn-primary btn-lg">提交</button>

        <!-- Large modal -->
        <%--<button type="button" class="btn btn-primary btn-lg" data-toggle="modal" data-target=".bs-example-modal-lg">须知</button>--%>

        <%--<div class="modal fade bs-example-modal-lg" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel">--%>
        <%--<div class="modal-dialog modal-lg" role="document">--%>
        <%--<div class="modal-content">--%>
        <%--<p>--%>
        <%--注意 : 目前仅可使用充值卡充值, 任意激活产品或充值卡仅可使用一次. 充值成功后将存入余额, 余额可在本商城无限制使用(与人民币比例1:1).--%>
        <%--余额不可提现, 转账.--%>
        <%--</p>--%>
        <%--</div>--%>
        <%--</div>--%>
        <%--</div>--%>

        <button class="btn btn-primary btn-lg " type="button" data-toggle="collapse" data-target="#collapseExample"
                aria-expanded="false" aria-controls="collapseExample">
            须知
        </button>
        <p></p>
        <div class="collapse" id="collapseExample">
            <div class="well">
                <p>
                    注意 : 目前仅可使用充值卡充值, 任意激活产品或充值卡仅可使用一次. 充值成功后将存入余额, 余额可在本商城无限制使用.
                    余额不可提现, 转账.
                </p>
            </div>
        </div>
    </div>

</form>

<%--<form action="/UserServlet" method="post">--%>

<%--<input type="hidden" name="method" value="recharge">--%>

<%--<label>输入充值卡卡密:</label>--%>

<%--<input type="text" name="rechargeCard"> &nbsp;--%>

<%--<input type="submit" value="提交"> &nbsp;--%>

<%--<span style="color: red">${msg}</span>--%>

<%--</form>--%>


<%--<br><br><br><br><br>--%>
<%--<form action="/UserServlet" method="post">--%>

<%--<input type="hidden" name="method" value="gift">--%>

<%--<label>输入礼品卡卡密:</label>--%>

<%--<input type="text" name="giftCard">&nbsp;--%>

<%--<input type="submit" value="提交"> &nbsp;--%>

<%--<span style="color: red">${msgGift}</span>--%>
<%--</form>--%>

<%--<p>--%>
<%--注意 : 礼品卡同礼物凭证 ,可以直接下单相应的图书, 无需支付费用, 礼品卡同样只能使用一次.--%>
<%--</p>--%>
</body>
</html>
