
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="element_page" var="element_sidebar"/>
<fmt:message bundle="${element_sidebar}" key="header.en_lang" var="english"/>
<fmt:message bundle="${element_sidebar}" key="header.ru_lang" var="russian"/>
<fmt:message bundle="${element_sidebar}" key="sidebar.show_all_users" var="show_all_users"/>
<fmt:message bundle="${element_sidebar}" key="sidebar.search_user" var="search_user"/>
<fmt:message bundle="${element_sidebar}" key="sidebar.banned_users" var="banned_users"/>
<fmt:message bundle="${element_sidebar}" key="sidebar.show_orders" var="show_orders"/>
<fmt:message bundle="${element_sidebar}" key="sidebar.show_contracts" var="show_contracts"/>
<fmt:message bundle="${element_sidebar}" key="profile_page.logout" var="logout"/>
<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin_sidebar.css">
    <title>sidebar</title>
</head>

<body>

<div class="bar-container">

    <a href="${pageContext.request.contextPath}/controller?command=change_language&value=${russian}&url=${pageContext.request.requestURL}" class="btn lang">${russian}</a>
    <a href="${pageContext.request.contextPath}/controller?command=change_language&value=${english}&url=${pageContext.request.requestURL}" class="btn lang">${english}</a>
    <a href="${pageContext.request.contextPath}/controller?command=show_all_users" class="btn">${show_all_users}</a>
    <a href="${pageContext.request.contextPath}/controller?command=to_search_user" class="btn">${search_user}</a>
    <a href="${pageContext.request.contextPath}/controller?command=banned_users" class="btn">${banned_users}</a>
    <a href="${pageContext.request.contextPath}/controller?command=show_orders" class="btn">${show_orders}</a>
    <a href="${pageContext.request.contextPath}/controller?command=show_contracts" class="btn">${show_contracts}</a>
    <a href="${pageContext.request.contextPath}/controller?command=logout" class="btn">${logout}</a>
</div>
</body>
</html>
