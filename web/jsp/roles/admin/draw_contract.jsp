
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.locale}" />
<fmt:setBundle basename="element_page" var="element_draw"/>
<fmt:message bundle="${element_draw}" key="contract.current" var="current_level"/>
<fmt:message bundle="${element_draw}" key="contract.target" var="target"/>
<fmt:message bundle="${element_draw}" key="contract.guitar" var="guitar"/>
<fmt:message bundle="${element_draw}" key="contract.start" var="start"/>
<fmt:message bundle="${element_draw}" key="contract.elementary" var="elementary"/>
<fmt:message bundle="${element_draw}" key="contract.intermediate" var="intermediate"/>
<fmt:message bundle="${element_draw}" key="contract.advanced" var="advanced"/>
<fmt:message bundle="${element_draw}" key="contract.classical" var="classical"/>
<fmt:message bundle="${element_draw}" key="contract.western" var="western"/>
<fmt:message bundle="${element_draw}" key="contract.electric" var="electric"/>
<fmt:message bundle="${element_draw}" key="contract.bass" var="bass"/>
<fmt:message bundle="${element_draw}" key="contract.slide" var="slide"/>
<fmt:message bundle="${element_draw}" key="contract.ethno" var="ethno"/>

<fmt:message var="order_confirmed"  bundle="${element_draw}" key="order.order_confirmed"/>
<fmt:message   bundle="${element_draw}" key="draw.title" var="title"/>

<c:set var="email_order" value="${requestScope.userEmail}" scope="session"/>
<c:set var="genre_order" value="${requestScope.genre}" scope="session"/>

<!DOCTYPE html>
<html >
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/contr.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin_screen.css">
    <title>${title}</title>
</head>
<body>
<%@include file="admin_sidebar.jsp"%>
<div class="screen-container">
    <div class="screen-container_contract-container">
        <div class="contract-container_contract-form">
            <form action="${pageContext.request.contextPath}/controller" name="post">
                <div class="form-item">
                    <input type="text" name="Email"value="${requestScope.userEmail}" disabled class="txt">

                </div>
                <div class="form-item">
                    <select name="tutorEmail" class="tutor-select">tutor
                    <c:forEach var="tutor_email" items="${requestScope.tutors}">
                        <option value="${tutor_email}">${tutor_email}</option>
                    </c:forEach>
                    </select>
                </div>

                <div class="form-item">
                    <input type="text" name="genre" value="${requestScope.genre}" disabled class="txt" class="dte">

                </div>

                <div class="form-item">
                    <input type="date" name="start_date" required>
                </div>

                <div class="form-item">
                    <input type="date" name="end_date" required>
                </div>

                <div class="form-item">
                    <div class="select-item">
                        <div class="select-tittle">${current_level}:</div>
                        <select name="current_level" >
                            <option value="start">${start}</option>
                            <option value="elementary">${elementary}</option>
                            <option value="intermediate">${intermediate}</option>
                            <option value="advanced">${advanced}</option>
                        </select>
                    </div>

                </div>
                <div class="form-item">
                    <div class="select-item">
                        <div class="select-tittle">${target}:</div>
                        <select name="target_level" >
                            <option value="start">${start}</option>
                            <option value="elementary">${elementary}</option>
                            <option value="intermediate">${intermediate}</option>
                            <option value="advanced">${advanced}</option>
                        </select>
                    </div>

                </div>
                <div class="form-item">
                    <div class="select-item">
                        <div class="select-tittle">${guitar}:</div>
                        <select name="guitar_type" >
                            <option value="classical">${classical}</option>
                            <option value="western">${western}</option>
                            <option value="electric">${electric}</option>
                            <option value="bass">${bass}</option>
                            <option value="slide">${slide}</option>
                            <option value="ethno">${ethno}</option>
                        </select>
                    </div>

                </div>
                <div class="form-item">
                    <input type="submit" name="command" value="confirm contract">
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>
