
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.locale}" />
<fmt:setBundle basename="element_page" var="element_order"/>

<fmt:message bundle="${element_order}" key="order.title" var="title" />
<fmt:message bundle="${element_order}" key="order.order_confirmed" var="order_confirmed"/>
<fmt:message bundle="${element_order}" key="order.email" var="email"/>
<fmt:message bundle="${element_order}" key="order.genre" var="genre"/>
<fmt:message bundle="${element_order}" key="order.is_paid" var="is_paid"/>
<fmt:message bundle="${element_order}" key="order.is_confirmed" var="is_confirmed"/>

<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin_screen.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/orders.css">
    <title>${title}</title>
</head>
<body>
<%@include file="admin_sidebar.jsp"  %>
<div class="screen-container">
    <div class="table-wrapper">
        <table>
            <tr>
                <th>${email}</th>
                <th>${genre}</th>
                <th>${is_paid}</th>
                <th>${is_confirmed}</th>
            </tr>
            <input type="hidden" name="url" value="${pageContext.request.requestURL}">

            <c:forEach var="order" items="${requestScope.orders}">

                <form action="${pageContext.request.contextPath}/controller" method="get">
                    <input type="hidden" name="Email" value="${order.email}"/>
                    <input type="hidden" name="genre" value="${order.genre}"/>
                    <input type="hidden" name="order_id" value="${order.orderId}">
                    <tr>
                        <td>${order.email}</td>
                        <td>${order.genre}</td>
                        <td>${order.paid}</td>
                        <td>${order.confirmed}</td>
                        <td><input type="submit" name="command" value="confirm order"  class="ban-btn"/></td>
                        <td><input type="submit" name="command" value="draw"  class="ban-btn"/></td>
                        <c:if test="${order.confirmed == true}">
                            <td class="refr">${order_confirmed}</td>
                        </c:if>
                    </tr>
                </form>
            </c:forEach>
        </table>
    </div>
</div>
</body>
</html>
