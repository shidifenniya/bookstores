<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>图书列表</title>
    
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
	.icon {
		margin:10px 10px 65px 10px;
		border: solid 1px gray;
		width: 160px;
		height: 180px;
		text-align: center;
		float: left;

	}
</style>
  </head>
  
  <body>

  <c:forEach items="${list}" var="book">

      <div class="panel panel-default icon">
          <div class="panel-body">
              <a href="<c:url value='/BookServlet?method=load&bid=${book.bid}'/>"><img src="<c:url value='/${book.image}'/>" border="0"/></a>
          </div>
          <div class="panel-footer">
              <a href="<c:url value='/BookServlet?method=load&bid=${book.bid}'/>">${book.bname}</a>
          </div>
      </div>
  </c:forEach>

  </body>
 
</html>

