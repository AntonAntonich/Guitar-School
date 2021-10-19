
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.locale}" />
<fmt:setBundle basename="element_page" var="element_contract"/>

<fmt:message bundle="${element_contract}" key="contract.title" var="title" />
<fmt:message bundle="${element_contract}" key="contract.tutor" var="tutor" />
<fmt:message bundle="${element_contract}" key="contract.student" var="student" />
<fmt:message bundle="${element_contract}" key="contract.current" var="current" />
<fmt:message bundle="${element_contract}" key="contract.target" var="target" />
<fmt:message bundle="${element_contract}" key="contract.guitar" var="guitar" />
<fmt:message bundle="${element_contract}" key="contract.genre" var="genre" />
<fmt:message bundle="${element_contract}" key="contract.start" var="start" />
<fmt:message bundle="${element_contract}" key="contract.end" var="end" />

<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/show.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin_screen.css">
    <title>${title}</title>
</head>
<body>
<%@include file="admin_sidebar.jsp"%>
<div class="screen-container">
    <div class="table-wrapper">
        <table>
            <tr>
                <th>${tutor}</th>
                <th>${student}</th>
                <th>${current}</th>
                <th>${target}</th>
                <th>${guitar}</th>
                <th>${genre}</th>
                <th>${start}</th>
                <th>${end}</th>
            </tr>
            <c:forEach var="contract" items="${requestScope.contractList}">
                <tr>
                    <td>${contract.tutorEmail}</td>
                    <td>${contract.studentEmail}</td>
                    <td>${contract.currentLevel}</td>
                    <td>${contract.targetLevel}</td>
                    <td>${contract.guitarType}</td>
                    <td>${contract.genre}</td>
                    <td>${contract.startDate}</td>
                    <td>${contract.endDate}</td>
                </tr>

            </c:forEach>

        </table>
    </div>
</div>
</body>
</html>
