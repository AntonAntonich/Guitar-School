
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.locale}" />
<fmt:setBundle basename="element_page" var="element_u"/>

<fmt:message bundle="${element_u}" key="search.title" var="title" />
<fmt:message bundle="${element_u}" key="search.yes" var="yes" />
<fmt:message bundle="${element_u}" key="search.no" var="no" />
<fmt:message bundle="${element_u}" key="search.email" var="email" />
<fmt:message bundle="${element_u}" key="users.nickname" var="nickname" />
<fmt:message bundle="${element_u}" key="users.role" var="role" />
<fmt:message bundle="${element_u}" key="users.skill" var="skill" />
<fmt:message bundle="${element_u}" key="users.degree" var="degree" />
<fmt:message bundle="${element_u}" key="users.is_active" var="is_active" />
<fmt:message bundle="${element_u}" key="users.none" var="none" />
<fmt:message bundle="${element_u}" key="users.tutor" var="tutor" />
<fmt:message bundle="${element_u}" key="users.student" var="student" />
<fmt:message bundle="${element_u}" key="users.junior" var="junior" />
<fmt:message bundle="${element_u}" key="users.middle" var="middle" />
<fmt:message bundle="${element_u}" key="users.senior" var="senior" />

<fmt:setBundle basename="message" var="msg" />
<fmt:message bundle="${msg}" var="search" key="message.admin.search_user"/>
<c:set var="user" value="${sessionScope.user}"/>

<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin_screen.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/search.css">
    <title>${title}</title>
</head>
<body>

<%@include file="admin_sidebar.jsp"  %>
<div class="screen-container">
    <div class="search-container">
        <form action="${pageContext.request.contextPath}/controller" method="post">
            <input type="hidden" name="command" value="search_user">
            ${email}: <input type="text" name="Email">
            <input type="submit" value="search" class="smb-btn">
        </form>
    </div>
    <div class="message">
        <c:if test="${user.email == null}">
            <c:out value="${search}"/>
        </c:if>
    </div>
    <div class="table-wrapper">
        <form action="${pageContext.request.contextPath}/controller" method="get">
        <table>
            <tr>
                <th>${email}</th>
                <th>${nickname}</th>
                <th>${role}</th>
                <th>${skill}</th>
                <th>${degree}</th>
                <th>${is_active}</th>
            </tr>
                <form action="controller" method="get">
                    <input type="hidden" name="Email" value="${user.email}">
                    <input type="hidden" name="skill" value="${user.studentSkillLevel}">
                    <input type="hidden" name="url" value="${pageContext.request.requestURL}">
                    <tr>
                        <td >${user.email}</td>
                        <td >${user.nickName}</td>
                        <td>
                            <select  name="Role" >
                                <option value="${user.userRole}" selected>${user.userRole}</option>
                                <option value="none">${none}</option>
                                <option value="tutor" >${tutor}</option>
                                <option value="student" >${student}</option>
                            </select>
                        </td>
                        <td>${user.studentSkillLevel}</td>
                        <td>
                            <select  name="degree" >
                                <option value="${user.tutorDegree}">${user.tutorDegree}</option>
                                <option value="none" selected>${none}</option>
                                <option value="junior" >${junior}</option>
                                <option value="middle" >${middle}</option>
                                <option value="senior" >${senior}</option>
                            </select>
                        </td>
                        <td>
                            <select  name="activated" >
                                <option value="${user.active}">${user.active}</option>
                                <option value="yes" selected>${yes}</option>
                                <option value="no" >${no}</option>
                            </select>
                        </td>
                            <td><input type="submit" name="command" value="ban"  class="ban-btn"/></td>
                            <td><input type="submit" name="command" value="refresh" class="ban-btn"/></td>
                    </tr>
                </form>
        </table>
        <dib class="message refr">
            ${refreshMessage}
        </dib>
        <dib class="message refr">
            ${userBanned}
        </dib>

    </div>
</div>

</body>
</html>
