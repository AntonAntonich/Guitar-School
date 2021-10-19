<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.locale}"/>


<fmt:setBundle basename="element_page" var="element"/>
<fmt:message bundle="${element}" key="header.phone_number" var="phone_number"/>
<fmt:message bundle="${element}" key="header.header_title" var="header_title"/>
<fmt:message bundle="${element}" key="header.email" var="header_email"/>
<fmt:message bundle="${element}" key="header.en_lang" var="english"/>
<fmt:message bundle="${element}" key="header.ru_lang" var="russian"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/header.css">
    <title>header</title>
</head>
<body>
<header>
    <div class="header-container">

        <div class="header-content">

            <ul>
                <li class="tel"><a href="tel:${phone_number}" title="call">${phone_number}</a></li>
                <li><p class="school"><a href="${pageContext.request.contextPath}/controller?command=to_main_page">${header_title}</a></p></li>
                <li class="mail"><a href="mailto:${header_email}">${header_email}</a></li>

                <div class="lang-container">
                    <li class="language_element "><a href="${pageContext.request.contextPath}/controller?command=change_language&value=${russian}&url=${pageContext.request.requestURL}">${russian}</a></li>
                    <li class="language_element "><a href="${pageContext.request.contextPath}/controller?command=change_language&value=${english}&url=${pageContext.request.requestURL}">${english}</a></li>
                </div>

            </ul>
        </div>
    </div>
</header>

</body>
</html>
