
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="element_page" var="element_tutor" />
<fmt:message bundle="${element_tutor}" key="profile_page.tutor.title" var="title"/>
<fmt:message bundle="${element_tutor}" key="profile_page.name" var="name"/>
<fmt:message bundle="${element_tutor}" key="profile_page.tutor.get_degree" var="get_degree"/>
<fmt:message bundle="${element_tutor}" key="profile_page.logout" var="Logout"/>
<fmt:message bundle="${element_tutor}" key="profile_page.role" var="role"/>
<fmt:message bundle="${element_tutor}" key="profile_page.tutor.degree" var="degree"/>
<fmt:message bundle="${element_tutor}" key="profile_page.tutor.my_students" var="my_students"/>
<fmt:message bundle="${element_tutor}" key="profile_page.tutor.student" var="student"/>
<fmt:message bundle="${element_tutor}" key="profile_page.tutor.current" var="current"/>
<fmt:message bundle="${element_tutor}" key="profile_page.profile_avatar_url" var="avatar_url"/>
<fmt:message bundle="${element_tutor}" key="profile_page.profile_avatar_url" var="avatar_url"/>

<fmt:setBundle basename="message" var="msg" />
<fmt:message bundle="${msg}" var="not_ativated" key="message.account_not_active"/>


<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/show.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/profile_page.css">

    <title>${title}</title>
</head>
<body>
<%@include file="../header.jsp"%>
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
                            <a href="" class="btn">
                                <p>${get_degree}</p>
                            </a>
                        </div>
                        <div class="btn">
                            <a href="${pageContext.request.contextPath}/controller?command=logout" class="btn">
                                <p>${Logout}</p>
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
                            ${degree} ${sessionScope.userDegree}
                        </li>
                        <li class="user-professional-block_text">
                            <div class="btn my-course">
                                <a href="${pageContext.request.contextPath}/controller?command=show_students" class="btn">
                                    <p>${my_students}</p>
                                </a>
                            </div>

                        </li>
                    </ul>

                </div>
            </div>
            <div class="table-wrapper">
                <c:if test="${sessionScope.isStudentsPressed == true}">
                    <table>
                        <tr>
                            <th>${student}</th>
                            <th>${current}</th>
                        </tr>
                        <c:forEach var="contract" items="${requestScope.contractList}">
                            <form action="${pageContext.request.contextPath}/controller" method="post">
                                <input type="hidden" name="Email" value="${contract.studentEmail}">
                                <tr>
                                    <td>${contract.studentEmail}</td>
                                    <td>
                                        <select name="skill" >
                                            <option value="${contract.currentLevel}" selected>${contract.currentLevel}</option>
                                            <option value="start">start</option>
                                            <option value="elementary">elementary</option>
                                            <option value="intermediate">intermediate</option>
                                            <option value="advanced">advanced</option>
                                        </select>
                                    </td>
                                    <td>
                                        <input type="submit" name="command" value="update" class="btn">
                                    </td>
                                </tr>
                            </form>
                        </c:forEach>
                    </table>
                </c:if>
                <div style="font-family: sans-serif; font-size: 22px; color: red;">${requestScope.updMessage}</div>
            </div>
        </div>
    </div>
</div>
<%@include file="../footer.jsp"%>
</body>
</html>
