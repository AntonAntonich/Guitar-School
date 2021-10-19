<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="element_page" var="element_succesful_payment"/>


<fmt:message bundle="${element_succesful_payment}" key="payment_successful.tittle" var="successful_payment_tittle"/>
<fmt:message bundle="${element_succesful_payment}"  key="payment_successful.successful_payment_message" var="transferMessage"/>


<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/payment_success.css">
    <title>${successful_payment_tittle}</title>
</head>

<body>
<%@include file="header.jsp" %>
<div class="container">
    <div class="pay-msg">
        ${transferMessage}
    </div>
</div>
<%@include file="footer.jsp" %>
</body>
</html>
