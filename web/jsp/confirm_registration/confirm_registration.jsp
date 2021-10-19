
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="element_page" var="element_confirm"/>
<fmt:message bundle="${element_confirm}" key="confirm.msg" var="msg"/>
<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/confirm_registrtion.css">
    <title>Confirm registration</title>
</head>
<body>
<%@ include file="../header.jsp"%>
<div class="container">
    <div class="message">
    <span>
       ${msg}
    </span>
    </div>
</div>

<%@ include file="../footer.jsp"%>
</body>
</html>
