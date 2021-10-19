<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="element_page" var="element"/>
<fmt:message bundle="${element}" key="sign_in.title" var="title"/>
<fmt:message bundle="${element}" key="sign_in.email" var="email"/>
<fmt:message bundle="${element}" key="sign_in.pass" var="password"/>
<fmt:message bundle="${element}" key="sign_in.submit" var="submit"/>
<fmt:message bundle="${element}" key="sign_in.placeholder_email" var="p_email"/>
<fmt:message bundle="${element}" key="sign_in.placeholder_password" var="p_password"/>



<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/signin.css">
    <title>${title}</title>
</head>
<body>
<%@ include file="../header.jsp"%>
<main>
    <div class="page-wrapper">
        <div class="from-wrapper">
            <form action="${pageContext.request.contextPath}/controller" method="post" id="form">
                <div class="form-item">
                    <input type="hidden" name="command" value="signin">
                    <span class="input-name">${email}</span>

                    <input type="text" name="Email" placeholder="${p_email}" class="txt-input" required
                           id="user_email">
                    <span class="error-messages-js">

                        </span>
                    <span class="error-message-java">
                        ${errorMessage}
                    </span>

                </div>
                <div class="form-item">
                    <span class="input-name">${password}</span>
                    <input type="text" name="Password" placeholder="${p_password}" class="txt-input"
                           required

                           id="user_password">
                    <span class="error-messages-js">

                        </span>

                    <span class="error-message-java">
                        ${errorMessage}
                    </span>

                </div>
                <div class="form-item">
                    <input type="submit"
                           value="${submit}" class="submit-btn" id="submit-btn">
                </div>
            </form>
        </div>
    </div>
</main>
<script src="${pageContext.request.contextPath}/js/signin.js"></script>
<%@ include file="../footer.jsp"%>
</body>
</html>
