
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="element_page" var="element"/>
<fmt:message bundle="${element}" key="sign_up.title" var="title"/>
<fmt:message bundle="${element}" key="sign_up.email" var="email"/>
<fmt:message bundle="${element}" key="sign_up.pass" var="password"/>
<fmt:message bundle="${element}" key="sign_up.pass_rep" var="password_rep"/>
<fmt:message bundle="${element}" key="sign_up.name" var="name"/>
<fmt:message bundle="${element}" key="sign_up.role" var="role"/>
<fmt:message bundle="${element}" key="sign_up.role_type_tutor" var="tutor"/>
<fmt:message bundle="${element}" key="sign_up.role_type_student" var="student"/>
<fmt:message bundle="${element}" key="sign_up.submit" var="submit"/>
<fmt:message bundle="${element}" key="sign_up.placeholder_email" var="p_email"/>
<fmt:message bundle="${element}" key="sign_up.placeholder_password" var="p_password"/>
<fmt:message bundle="${element}" key="sign_up.placeholder_password_rep" var="p_password_rep"/>
<fmt:message bundle="${element}" key="sign_up.placeholder_name" var="p_name"/>

<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/signup.css">
    <title>Sign Up</title>
</head>
<body>
<%@ include file="../header.jsp"%>
<%--<jsp:include page="../header.jsp"/>--%>
<main>
    <div class="page-wrapper">
        <div class="from-wrapper">
            <form action="${pageContext.request.contextPath}/controller" method="post">
                <input type="hidden" name="command" value="signup">
                <div class="form-item">
                    <span class="input-name">${email}</span>
                    <input type="text" name="Email" placeholder="${p_email}" class="txt-input"
                    >
                    <span class="error-messages-js">

                   </span>
                    <span class="error-message-java">
                        ${errorEmailMessage}
                    </span>


                </div>
                <div class="form-item">
                    <span class="input-name">${password}</span>
                    <input type="text" name="Password" placeholder="${p_password}"
                           id="password0"
                           class="txt-input">
                    <span class="error-messages-js">

                   </span>
                    <span class="error-message-java">
                        ${errorPasswordMessage}
                    </span>

                </div>

                <div class="form-item">
                    <span class="input-name">${password_rep}</span>
                    <input type="text" name="Password-Repeated" placeholder="${p_password_rep}"
                           id="password1"
                           class="txt-input">
                    <span class="error-messages-js">

                   </span>
                    <span class="error-message-java">
                        ${errorRepeatedPasswordMessage}
                    </span>

                </div>

                <div class="form-item">
                    <span class="input-name">${name}</span>
                    <input type="text" name="Nickname" placeholder="${p_name}" class="txt-input"
                    >
                    <span class="error-messages-js">

                   </span>

                    <span class="error-message-java">
                        ${errorNameMessage}
                    </span>

                </div>
                <div class="form-item">
                    <span class="input-name">${role}</span>
                    <select name="Role_id" class="role-select">
                        <option value="1">${tutor}</option>
                        <option value="2">${student}</option>
                    </select>
                </div>

                <div class="form-item">
                    <input type="submit" value="${submit}" class="submit-btn" id="submit-btn">
                </div>
            </form>

        </div>
    </div>

</main>
<script src="${pageContext.request.contextPath}/js/signup.js"></script>
<%--<jsp:include page="../footer.jsp"/>--%>
<%@ include file="../footer.jsp"%>
</body>
</html>
