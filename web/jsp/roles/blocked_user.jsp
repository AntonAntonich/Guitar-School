
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.locale}" />
<fmt:setBundle basename="element_page" var="element_blocked"/>
<fmt:message bundle="${element_blocked}" key="blocked.title" var="title"/>
<fmt:message bundle="${element_blocked}" key="blocked.msg" var="msg"/>
<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/blocked.css">

    <title>${title}</title>
</head>
<body>
<%@include file="../header.jsp"%>
    <div class="screen-container">
        <div class="txt-container">
            <p style="font-family: sans-serif; font-size: 50px; font-weight: 700">${msg}</p>
        </div>

    </div>
<%@include file="../footer.jsp"%>
</body>
</html>
