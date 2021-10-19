<%--
  Created by IntelliJ IDEA.
  User: Anton
  Date: 08.10.2021
  Time: 15:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page isErrorPage="true" contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/error.css">
    <title>Error</title>
</head>
<body>
<%@include file="../header.jsp"%>
<div class="screen">
    <div class="exception-msg-container">
       <ul>
           <li>Request from ${pageContext.errorData.requestURI} </li>
           <li>Servlet name: ${pageContext.errorData.servletName}</li>
           <li>Status code: ${pageContext.errorData.statusCode}</li>
           <li>Exception: ${pageContext.exception}</li>
           <li>Message from exception: ${pageContext.exception.message}</li
       </ul>
    </div>
    <div class="btn">
        <a href="${pageContext.request.contextPath}/controller?command=to_main_page">main</a>
    </div>
</div>
<%@include file="../footer.jsp"%>
</body>
</html>
