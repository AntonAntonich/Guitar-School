<%--
  Created by IntelliJ IDEA.
  User: Anton
  Date: 19.10.2021
  Time: 14:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/error.css">
    <title>404</title>
</head>
<body>
<%@include file="../header.jsp"%>
<div class="main">
    <div class="screen-not">
        <div class="btn-not">
            <a href="${pageContext.request.contextPath}/controller?command=to_main_page">main</a>
        </div>
    </div>
</div>

<%@include file="../header.jsp"%>
</body>
</html>
