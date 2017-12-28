<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>订单详细</title>

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
        /** {*/
        /*font-size: 11pt;*/
        /*}*/

        /*div {*/
        /*border: solid 2px gray;*/
        /*width: 75px;*/
        /*height: 75px;*/
        /*text-align: center;*/
        /*}*/

        /*li {*/
        /*margin: 10px;*/
        /*}*/

        <%--#pay {--%>
        <%--background: url(<c:url value='/images/all.png'/>) no-repeat;--%>
        <%--display: inline-block;--%>

        <%--background-position: 0 -412px;--%>
        <%--margin-left: 30px;--%>
        <%--height: 36px;--%>
        <%--width: 146px;--%>
        <%--}--%>

        <%--#pay:HOVER {--%>
        <%--background: url(<c:url value='/images/all.png'/>) no-repeat;--%>
        <%--display: inline-block;--%>

        <%--background-position: 0 -448px;--%>
        <%--margin-left: 30px;--%>
        <%--height: 36px;--%>
        <%--width: 146px;--%>
        <%--}--%>
    </style>


</head>

<body>

<div class="panel panel-default">
    <!-- Default panel contents -->
    <div class="panel-heading">订单编号：${order.oid}</div>
    <div class="panel-body">
        <p>成交时间：${order.ordertime}</p>
        <p>金额：<span style="color: red">

                <fmt:formatNumber type="number" value="${order.total}" maxFractionDigits="2"/>

                </span>元</p>
    </div>

    <!-- Table -->
    <table class="table">
        <c:forEach items="${order.orderItemList}" var="orderItem">
            <tr align="center">
                <td width="15%">
                    <div><img src="<c:url value='/${orderItem.book.image}'/>" height="75"/></div>
                </td>
                <td>书名：${orderItem.book.bname}</td>
                <td>单价：${orderItem.book.price}元</td>
                <td>作者：${orderItem.book.author}</td>
                <td>数量：${orderItem.count}</td>
                <td>小计：

                    <fmt:formatNumber type="number" value="${orderItem.subtotal}" maxFractionDigits="2"/>

                    元</td>
            </tr>
        </c:forEach>
    </table>
</div>

<form method="post" action="/OrderServlet" id="form" target="body">
    <input type="hidden" name="method" value="pay">
    <input type="hidden" name="oid" value="${order.oid}">
    <div class="form-group">
        <%--<select name="province" id="p" class="form-control">--%>
        <%--<option>请选择省</option>--%>
        <%--</select>--%>
        <%--　　　--%>
        <%--<select name="city" id="c" class="form-control">--%>
        <%--<option>请选择市</option>--%>
        <%--</select>--%>

        <input class="form-control" placeholder="Address" type="text" name="address" size="50" value=""/>
        <c:if test="${not empty msgAddress}">
            <div class="alert alert-danger" role="alert" style="margin-top: 10px">
                <span class="glyphicon glyphicon-remove"></span>&nbsp;
                    ${msgAddress}</div>

        </c:if>

        <br/>
    </div>
    <div class="form-group" style="margin-left: 30px">
        <label>请选择支付方式:</label>
        <div class="radio" data-toggle="buttons">
            <%--<input type="radio" name="pd_FrpId" value="ICBC-NET-B2C"/>工商银行--%>
            <%--<img src="../../bank_img/icbc.bmp" align="middle" style="margin-right: 30px"/>--%>
            <%--<input type="radio" name="pd_FrpId" value="BOC-NET-B2C"/>中国银行--%>
            <%--<img src="../../bank_img/bc.bmp" align="middle"/>--%>

            <label class="btn">
                <input type="radio" name="pd_FrpId" value="ICBC-NET-B2C" autocomplete="off"/>
                <img src="../../newBank_img/gonghang.gif" align="middle" width="42" height="30"
                     style="margin-left: -5px"/>
                工商银行
            </label>
            <label class="btn">
                <input type="radio" name="pd_FrpId" value="BOC-NET-B2C" autocomplete="off"/>
                <img src="../../newBank_img/zhongguo.jpg" align="middle" width="30" height="30"
                     style="margin-left: 20px;margin-right: 10px"/>
                中国银行
            </label>

            <br/><br/>
            <%--<input type="radio" name="pd_FrpId" value="ABC-NET-B2C"/>农业银行--%>
            <%--<img src="../../bank_img/abc.bmp" align="middle" style="margin-right: 30px"/>--%>
            <%--<input type="radio" name="pd_FrpId" value="CCB-NET-B2C"/>建设银行--%>
            <%--<img src="../../bank_img/ccb.bmp" align="middle"/>--%>

            <label class="btn">
                <input type="radio" name="pd_FrpId" value="ABC-NET-B2C" autocomplete="off"/>
                <img src="../../newBank_img/nonghang.jpg" align="middle" width="30" height="30"
                     style="margin-right: 8px"/>
                农业银行
            </label>
            <label class="btn">
                <input type="radio" name="pd_FrpId" value="CCB-NET-B2C" autocomplete="off"/>
                <img src="../../newBank_img/jianhang.jpg" align="middle" width="30" height="30"
                     style="margin-left: 20px;margin-right: 10px"/>
                建设银行
            </label>
            <br/><br/>
            <label class="btn">
                <input type="radio" name="pd_FrpId" value="BOCO-NET-B2C" autocomplete="off"/>
                <img src="../../newBank_img/jiaohang.jpg" width="40" height="38" align="middle"
                     style="margin-left: -3px"/>
                交通银行
            </label>

            <label class="btn active">
                <input type="radio" name="pd_FrpId" value="balance" autocomplete="off" checked/>
                <img src="../../bank_img/yve.jpg" width="60" height="33" align="middle" style="margin-left: 5px"/>
                余额支付
            </label>

        </div>
    </div>
</form>
<a id="pay" href="javascript:document.getElementById('form').submit();" class="btn btn-primary btn-lg">

    快速支付

</a>
<c:if test="${not empty msgPay}">
    <span class="alert alert-warning" role="alert" style="margin-left: 10px">
    <span class="glyphicon glyphicon-remove"></span>
            ${msgPay}</span>
</c:if>

<%--<table border="1" width="100%" cellspacing="0" background="black">--%>
<%--<tr bgcolor="gray" bordercolor="gray">--%>
<%--<td colspan="6">--%>
<%--订单编号：${order.oid}　成交时间：${order.ordertime}　金额：<font color="red"><b>${order.total}元</b></font>--%>
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
<%--</table>--%>
<%--<br/>--%>
<%--<form method="post" action="/OrderServlet" id="form" target="body">--%>
<%--<input type="hidden" name="method" value="pay">--%>
<%--<input type="hidden" name="oid" value="${order.oid}">--%>

<%--<select name="province" id="p">--%>
<%--<option>请选择省</option>--%>
<%--</select>--%>
<%--　　　--%>
<%--<select name="city" id="c">--%>
<%--<option>请选择市</option>--%>
<%--</select>--%>

<%--收货地址：<input type="text" name="address" size="50" value=""/> <span style="color: red">${msgAddress}</span> <br/>--%>

<%--选择银行：<br/>--%>
<%--<input type="radio" name="pd_FrpId" value="ICBC-NET-B2C"/>工商银行--%>
<%--<img src="../../bank_img/icbc.bmp" align="middle"/>--%>
<%--<input type="radio" name="pd_FrpId" value="BOC-NET-B2C"/>中国银行--%>
<%--<img src="../../bank_img/bc.bmp" align="middle"/><br/><br/>--%>
<%--<input type="radio" name="pd_FrpId" value="ABC-NET-B2C"/>农业银行--%>
<%--<img src="../../bank_img/abc.bmp" align="middle"/>--%>
<%--<input type="radio" name="pd_FrpId" value="CCB-NET-B2C"/>建设银行--%>
<%--<img src="../../bank_img/ccb.bmp" align="middle"/><br/><br/>--%>
<%--<input type="radio" name="pd_FrpId" value="BOCO-NET-B2C"/>交通银行--%>
<%--<img src="../../bank_img/bcc.bmp" align="middle"/>--%>

<%--<input type="radio" name="pd_FrpId" value="balance" checked="checked"/>余额支付--%>
<%--<img src="../../bank_img/yve.jpg" width="154" height="33" align="middle"/>--%>

<%--<br/>--%>
<%--</form>--%>
<%--<a id="pay" href="javascript:document.getElementById('form').submit();"></a>--%>
<%--<span style="color: red">${msgPay}</span>--%>
</body>
<script type="text/javascript">

    function createXMLHttpRequest() {
        try {
            return new XMLHttpRequest();//大多数浏览器
        } catch (e) {
            try {
                return ActvieXObject("Msxml2.XMLHTTP");//IE6.0
            } catch (e) {
                try {
                    return ActvieXObject("Microsoft.XMLHTTP");//IE5.5及更早版本
                } catch (e) {
                    alert("哥们儿，您用的是什么浏览器啊？");
                    throw e;
                }
            }
        }
    }
    /*
     * 1. 在文档加载完毕时发送请求，得到所有省份名称，显示在<select name="province"/>中
     * 2. 在选择了新的省份时，发送请求（参数为省名称），得到xml文档，即<province>元素
     *   解析xml文档，得到其中所有的<city>，再得到每个<city>元素的内容，即市名，使用市名生成<option>，插入到<select name="city">元素中
     */

    window.onload = function () {
        /*
         ajax四步，请求ProvinceServlet，得到所有省份名称
         使用每个省份名称创建一个<option>元素，添加到<select name="province">中
         */
        var xmlHttp = createXMLHttpRequest();
        xmlHttp.open("GET", "<c:url value="/ProvinceServlet"/>", true);
        xmlHttp.send(null);
        xmlHttp.onreadystatechange = function () {
            if (xmlHttp.readyState == 4 && xmlHttp.status == 200) {
                // 获取服务器的响应
                var text = xmlHttp.responseText;
                // 使用逗号分隔它，得到数组
                var arr = text.split(",");
                // 循环遍历每个省份名称，每个名称生成一个option对象，添加到<select>中
                for (var i = 0; i < arr.length; i++) {
                    var op = document.createElement("option");//创建一个指定名称元素
                    op.value = arr[i];//设置op的实际值为当前的省份名称
                    var textNode = document.createTextNode(arr[i]);//创建文本节点
                    op.appendChild(textNode);//把文本子节点添加到op元素中，指定其显示值

                    document.getElementById("p").appendChild(op);
                }
            }
        };


        /*
         第二件事情：给<select name="province">添加改变监听
         使用选择的省份名称请求CityServlet，得到<province>元素(xml元素)！！！
         获取<province>元素中所有的<city>元素，遍历之！获取每个<city>的文本内容，即市名称
         使用每个市名称创建<option>元素添加到<select name="city">
         */
        var proSelect = document.getElementById("p");
        proSelect.onchange = function () {
            var xmlHttp = createXMLHttpRequest();
            xmlHttp.open("POST", "<c:url value='/CityServlet'/>", true);
            xmlHttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
            xmlHttp.send("pname=" + proSelect.value);//把下拉列表中选择的值发送给服务器！
            xmlHttp.onreadystatechange = function () {
                if (xmlHttp.readyState == 4 && xmlHttp.status == 200) {
                    /*
                     把select中的所有option移除（除了请选择）
                     */
                    var citySelect = document.getElementById("c");
                    // 获取其所有子元素
                    var optionEleList = citySelect.getElementsByTagName("option");
                    // 循环遍历每个option元素，然后在citySelect中移除
                    while (optionEleList.length > 1) {//子元素的个数如果大于1就循环，等于1就不循环了！
                        citySelect.removeChild(optionEleList[1]);//总是删除1下标，因为1删除了，2就变成1了！
                    }


                    var doc = xmlHttp.responseXML;
                    // 得到所有名为city的元素
                    var cityEleList = doc.getElementsByTagName("city");
                    // 循环遍历每个city元素
                    for (var i = 0; i < cityEleList.length; i++) {
                        var cityEle = cityEleList[i];//得到每个city元素
                        var cityName;
                        // 获取市名称
                        if (window.addEventListener) {//处理浏览器的差异
                            cityName = cityEle.textContent;//支持FireFox等浏览器
                        } else {
                            cityName = cityEle.text;//支持IE
                        }

                        // 使用市名称创建option元素，添加到<select name="city">中
                        var op = document.createElement("option");
                        op.value = cityName;
                        // 创建文本节点
                        var textNode = document.createTextNode(cityName);
                        op.appendChild(textNode);//把文本节点追加到op元素中

                        //把op添加到<select>元素中
                        citySelect.appendChild(op);
                    }
                }
            };
        };
    };
</script>
</html>

