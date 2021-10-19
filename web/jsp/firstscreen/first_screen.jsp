<%@ page import="com.anton.gs.controller.command.SessionAttribute" %>
<%@ page import="com.anton.gs.controller.RoleType" %>
<%@ page import="com.anton.gs.controller.command.PageAddress" %><%--
  Created by IntelliJ IDEA.
  User: Anton
  Date: 29.07.2021
  Time: 14:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="element_page" var="element" />
<fmt:message bundle="${element}" var="title" key="first_screen.title"/>
<fmt:message bundle="${element}" var="signIn" key="first_screen.nav.signin"/>
<fmt:message bundle="${element}" var="signUp" key="first_screen.nav.signup"/>
<fmt:message bundle="${element}" var="about" key="first_screen.nav.about"/>
<fmt:message bundle="${element}" var="offer" key="first_screen.offer"/>
<fmt:message bundle="${element}" var="cta" key="first_screen.cta"/>
<fmt:message bundle="${element}" var="follow" key="first_screen.follow_us"/>

<html>
<head>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css">
    <title>${title}</title>

</head>
<body>

<jsp:include page="../header.jsp"/>
<main class="main">
    <div class="main-container">
        <div class="nav">
            <div class="nav-item">
                <a href="${pageContext.request.contextPath}/controller?command=to_signin_page">${signIn}</a>
            </div>
            <div class="nav-item">
                <a href="${pageContext.request.contextPath}/controller?command=to_signup_page">${signUp}</a>
            </div>
            <div class="nav-item">
                <a href="${pageContext.request.contextPath}/controler?command=to_about_page">${about}</a>
            </div>
        </div>

        <div class="section-content">
            <div class="offer">
                <p class="camp">${offer}</p>
                <p class="message">${cta}</p>
                <a href="controller?command=to_cards" class="join">${follow}</a>
            </div>
            <div class="pictures-container">
                <div class="el">
                    <img src="${pageContext.request.contextPath}/img/el_guitar.jpg" class="el-img" alt="">
                </div>
            </div>
        </div>
    </div>
</main>
<jsp:include page="../footer.jsp"/>

</body>
</html>
