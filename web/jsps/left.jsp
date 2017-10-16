<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>left</title>
    <base target="body"/>
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
	  <link href="../BS/css/bootstrap.min.css" rel="stylesheet">
	  <script src="../BS/js/jquery-3.2.1.js"></script>
	  <script src="../BS/js/bootstrap.min.js"></script>
	<style type="text/css">
		*{
			font-size:10pt;
			text-align: center;
		}
		div {
			/*background: #87CEFA; */
			margin: 3px; 
			padding: 3px;
		}
		a {
			text-decoration: none;
		}
	</style>
  </head>
  
  <body>

  <ul class="nav nav-pills nav-stacked">

	  <li role="presentation"><a href="<c:url value='/BookServlet?method=findAll'/>">全部分类</a></li>

	  <c:forEach items="${category}" var="cg">
		  <li role="presentation">
			  <a href="<c:url value='/BookServlet?method=findByCategory&cname=${cg.cname}'/>">${cg.cname}</a>
		  </li>
	  </c:forEach>

  </ul>

  </body>
</html>
