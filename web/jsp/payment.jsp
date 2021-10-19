
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.locale}" />
<fmt:setBundle basename="element_page" var="element_payment" />
<fmt:message bundle="${element_payment}" key="payment.tittle" var="payment_tittle"/>
<fmt:message bundle="${element_payment}" key="payment.enter_card_number" var="enter_card"/>
<fmt:message bundle="${element_payment}" key="payment.enter_sum" var="enter_sum"/>


<c:set var="none" value="NONE" scope="session"/>
<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/payment.css">
    <title>${payment_tittle}</title>
</head>
<body>
<%@ include file="header.jsp"%>
<c:if test="${sessionScope.userRole == none}">
    <jsp:forward page="../index.jsp"/>
</c:if>

<div class="container">
    <div class="payment-container">
        <div class="form-container">
            <form action="${pageContext.request.contextPath}/controller" method="post">
                <div class="card-number">
                    <div class="card-number-tittle">
                        ${enter_card}
                    </div>
                    <input type="text" name="bank_account_number" class="card-field">
                </div>
                <div class="rest">

                    <input type="submit" name="command" value="rest" class="btn">
                    <div class="rest-msg">
                        ${sessionScope.restMessage} ${sessionScope.rest}
                    </div>
                </div>
                <div class="pay">
                    <div class="sum-tittle">
                        ${enter_sum}
                    </div>
                    <input type="text" name="sum" value="${sessionScope.price}" class="card-field sum-field"  disabled>
                    <input type="submit" name="command" value="pay" class="btn">
                    <div class="pay-msg">
                        ${sessionScope.transferMessage}
                    </div>
                </div>

            </form>
        </div>
    </div>

</div>

<%@ include file="footer.jsp"%>
</body>
</html>
