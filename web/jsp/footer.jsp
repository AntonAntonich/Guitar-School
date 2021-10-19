
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="element_page" var="element"/>
<fmt:message bundle="${element}" key="footer.phone_number" var="phone_number"/>
<fmt:message bundle="${element}" key="footer.header_title" var="footer_title"/>
<fmt:message bundle="${element}" key="footer.email" var="footer_email"/>


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/footer.css">

</head>
<body>
<div class="page">
    <footer class="footer">
        <div class="footer-container">
            <div class="footer-content">
                <ul>
                    <li class="tel">
                        <a href="tel:${phone_number}" title="call">${phone_number}</a>
                    </li>
                    <li>
                        <p class="tittle">
                            ${footer_title}
                        </p>
                    </li>

                    <li class="mail">
                        <a href="mailto:${footer_email}">${footer_email}</a>
                    </li>
                </ul>
                </ul>
            </div>
        </div>
    </footer>
</div>

</body>
</html>