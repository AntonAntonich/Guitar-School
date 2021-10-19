
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.locale}" />
<fmt:setBundle basename="element_page" var="element_banned"/>
<fmt:message bundle="${element_banned}" key="banned.title" var="title" />
<fmt:message bundle="${element_banned}" key="banned.email" var="email" />

<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin_screen.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/search.css">
    <title>${title}</title>
</head>
<body>
<%@include file="admin_sidebar.jsp"  %>
<div class="screen-container">
    <div class="table-wrapper">
        <table>
            <tr>
                <th>${email}</th>
            </tr>
            <input type="hidden" name="url" value="${pageContext.request.requestURL}">
            <c:forEach var="banned_user_email" items="${requestScope.bannedUsers}">
                <form action="controller" method="get">
                    <input type="hidden" name="Email" value="${banned_user_email}"/>
                    <tr>
                        <td>
                            ${banned_user_email}
                        </td>
                        <td><input type="submit" name="command" value="unban"  class="ban-btn"/></td>
                        <c:if test="${banned_user_email == requestScope.unbannedUser}">
                            <td class="refr">${userUnbanned}</td>
                        </c:if>
                    </tr>
                </form>
            </c:forEach>
        </table>
    </div>
</div>
</body>
</html>
