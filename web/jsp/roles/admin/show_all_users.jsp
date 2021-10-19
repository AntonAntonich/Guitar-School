
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.locale}" />
<fmt:setBundle basename="element_page" var="element_users"/>
<fmt:message bundle="${element_users}" key="users.title" var="title" />
<fmt:message bundle="${element_users}" key="users.email" var="email" />
<fmt:message bundle="${element_users}" key="users.nickname" var="nickname" />
<fmt:message bundle="${element_users}" key="users.role" var="role" />
<fmt:message bundle="${element_users}" key="users.skill" var="skill" />
<fmt:message bundle="${element_users}" key="users.degree" var="degree" />
<fmt:message bundle="${element_users}" key="users.is_active" var="is_active" />
<fmt:message bundle="${element_users}" key="users.none" var="none" />
<fmt:message bundle="${element_users}" key="users.tutor" var="tutor" />
<fmt:message bundle="${element_users}" key="users.student" var="student" />
<fmt:message bundle="${element_users}" key="users.junior" var="junior" />
<fmt:message bundle="${element_users}" key="users.middle" var="middle" />
<fmt:message bundle="${element_users}" key="users.senior" var="senior" />

<c:set var="tutor" value="tutor" />

<html>

<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/show.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin_screen.css">
    <title>${title}</title>
</head>
<body>
<%@include file="admin_sidebar.jsp"  %>
<div class="screen-container">
    <div class="table-wrapper">
        <table>
            <tr>
                <th>${email}</th>
                <th>${nickname}</th>
                <th>${role}</th>
                <th>${skill}</th>
                <th>${degree}</th>
                <th>${is_active}</th>
            </tr>
            <c:forEach var="user" items="${requestScope.users}">
                <form action="controller" method="get">
                    <input type="hidden" name="Email" value="${user.email}">
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
                            <c:choose>
                                <c:when test="${user.userRole==tutor}">
                                    <select name="Role">
                                        <option value="${user.tutorDegree}">${user.tutorDegree}</option>
                                        <option value="none" selected>${none}</option>
                                        <option value="junior" >${junior}</option>
                                        <option value="middle" >${middle}</option>
                                        <option value="senior" >${senior}</option>
                                    </select>
                                </c:when>
                                <c:otherwise>
                                    ${none}
                                </c:otherwise>
                            </c:choose>
                        </td>
                        <td>${user.active}</td>
<%--                        <td><input type="submit" name="command" value="ban"  class="ban-btn"/></td>--%>
<%--                        <td><input type="submit" name="command" value="refresh" class="ban-btn"/></td>--%>
<%--                        <td>${refreshMessage}</td>--%>
                    </tr>
                </form>
            </c:forEach>
        </table>
    </div>
</div>
</body>
</html>
