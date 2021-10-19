<%@ page import="com.anton.gs.controller.command.SessionAttribute" %>
<%@ page import="com.anton.gs.controller.RoleType" %>
<%@ page import="com.anton.gs.controller.command.PageAddress" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.locale}" />
<fmt:setBundle basename="element_page" var="element_student"/>
<fmt:message bundle="${element_student}" key="profile_page.student.title" var="title"/>
<fmt:message bundle="${element_student}" key="profile_page.name" var="name"/>
<fmt:message bundle="${element_student}" key="profile_page.student.buy" var="buy"/>
<fmt:message bundle="${element_student}" key="profile_page.logout" var="logut"/>
<fmt:message bundle="${element_student}" key="profile_page.role" var="role"/>
<fmt:message bundle="${element_student}" key="profile_page.student.skill" var="skill"/>
<fmt:message bundle="${element_student}" key="profile_page.student.my_courses" var="my_courses"/>
<fmt:message bundle="${element_student}" key="profile_page.student.tutor" var="tutor"/>
<fmt:message bundle="${element_student}" key="profile_page.student.current" var="current"/>
<fmt:message bundle="${element_student}" key="profile_page.student.target" var="target"/>
<fmt:message bundle="${element_student}" key="profile_page.student.guitar" var="guitar"/>
<fmt:message bundle="${element_student}" key="profile_page.student.genre" var="genre"/>
<fmt:message bundle="${element_student}" key="profile_page.student.start" var="start"/>
<fmt:message bundle="${element_student}" key="profile_page.student.end" var="end"/>
<fmt:message bundle="${element_student}" key="profile_page.profile_avatar_url" var="avatar_url"/>
<fmt:message bundle="${element_student}" key="profile_page.profile_avatar_url" var="avatar_url"/>

<fmt:setBundle basename="message" var="msg" />
<fmt:message bundle="${msg}" var="not_ativated" key="message.account_not_active"/>


<c:set var="to_cards" value="${pageContext.request.contextPath}/controller?command=to_cards"/>

<c:set var="none" value="NONE" scope="session"/>


<html>

<head>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/profile_page.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/show.css">
    <title>${title}</title>
</head>

<body>

<%@ include file="../header.jsp"%>
<div class="wrapper">
    <div class="content">
        <div class=not-active-msg>
            <c:if test="${sessionScope.isActive == false}">
                ${not_ativated}
            </c:if>
        </div>
        <div class="user-info-block">
            <div class="container">
                <div class="user-info-block_row">
                    <div class="user-info-block_avatar-photo">
                        <form action="${pageContext.request.contextPath}/upload_controller" method="post" enctype="multipart/form-data">
                            <input type="hidden" name="url" value="${pageContext.request.requestURL}">
                            <label style="display: block;
                                   width:200px;
                                   height:200px;
                                   background-image: url(${pageContext.request.contextPath}${avatar_url}${sessionScope.avatarFileName});
                                   background-repeat: no-repeat;
                                   background-size: 200px 200px;
                                   cursor: pointer;">
                                <input type="file" name="file"/>
                            </label>
                            <input type="submit" name="command" style="width:200px;
                                                        margin-top:15px;
                                                        font-size: 20px;" value="change avatar"/>
                        </form>
                    </div>
                    <div class="user-info-block_text">
                        <ul class="user-info-block_text_list">
                            <li class="user-info-block_text_item">
                                ${name} ${sessionScope.userNickname}
                            </li>
                        </ul>
                        <div class="btn">
                            <a href="${to_cards}" class="btn">
                                <p>${buy}</p>
                            </a>
                        </div>

                        <div class="btn">
                            <a href="${pageContext.request.contextPath}/controller?command=logout" class="btn">
                                <p>${logut}</p>
                            </a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="user-professional-block">
            <div class="container">
                <div class="user-professional-block_text">
                    <ul class="user-professional-block_text_list">
                        <li class="user-professional-block_text_item">
                            ${role} ${sessionScope.userRole}
                        </li>
                        <li class="user-professional-block_text_item">
                            ${skill} ${sessionScope.userSkill}
                        </li>
                        <li class="user-professional-block_text">
                            <div class="my-course-btn">
                                <a href="${pageContext.request.contextPath}/controller?command=show_courses">
                                    <p>${my_courses}</p>
                                </a>
                            </div>

                        </li>
                    </ul>

                </div>
            </div>
            <div class="table-wrapper">
                <c:if test="${sessionScope.isCoursesPressed==true}">
                    <table>
                        <tr>
                            <th>${tutor}</th>
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
                                <td>${contract.currentLevel}</td>
                                <td>${contract.targetLevel}</td>
                                <td>${contract.guitarType}</td>
                                <td>${contract.genre}</td>
                                <td>${contract.startDate}</td>
                                <td>${contract.endDate}</td>
                            </tr>
                        </c:forEach>
                    </table>
                </c:if>
            </div>
        </div>
    </div>
</div>
<%@include file="../footer.jsp"%>
</body>
</html>
