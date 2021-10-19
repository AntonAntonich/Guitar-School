
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setBundle basename="element_page" var="element_contract_success"/>
<fmt:setLocale value="${sessionScope.locale}" />

<fmt:message bundle="${element_contract_success}" key="contract_success.title" var="title" />
<fmt:message bundle="${element_contract_success}" key="contract_success.contract_confirmed" var="contract_confirmed" />


<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin_screen.css">
    <title>${title}</title>
</head>
<body>
<%@include file="admin_sidebar.jsp"%>
<div class="screen-container">
    <div class="msg-container">
        ${contract_confirmed}
    </div>
</div>
</body>
</html>
