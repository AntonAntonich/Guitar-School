
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="element_page" var="element_succesful_registration"/>
<fmt:message bundle="${element_succesful_registration}" key="registration_successful.title" var="title"/>
<fmt:message bundle="${element_succesful_registration}" key="registration_successful.msg" var="msg"/>
<fmt:message bundle="${element_succesful_registration}" key="registration_successful.btn" var="btn"/>
<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/payment_success.css">
    <title>${title}</title>
</head>
<body>
<%@include file="../header.jsp" %>
<div class="container">
    <div class="pay-msg">
        ${msg}
    </div>
    <div class="to_main_btn">
        <a href="${pageContext.request.contextPath}/controller?command=to_main_page">${btn}</a>
    </div>
</div>

<%@include file="../footer.jsp" %>
</body>
</html>
