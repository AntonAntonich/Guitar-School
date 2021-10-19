<%--
  Created by IntelliJ IDEA.
  User: Anton
  Date: 24.09.2021
  Time: 3:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="element_page" var="element_cards"/>
<fmt:message var="metal" key="buy.product.metal" bundle="${element_cards}"/>
<fmt:message var="rock" key="buy.product.rock" bundle="${element_cards}"/>
<fmt:message var="blues" key="buy.product.blues" bundle="${element_cards}"/>
<fmt:message var="jazz" key="buy.product.jazz" bundle="${element_cards}"/>
<fmt:message var="funk" key="buy.product.funk" bundle="${element_cards}"/>
<fmt:message var="ethnical" key="buy.product.ethnical_strings" bundle="${element_cards}"/>
<fmt:message var="flamenco" key="buy.product.flamenco" bundle="${element_cards}"/>
<fmt:message var="pop" key="buy.product.pop" bundle="${element_cards}"/>
<fmt:message var="classical" key="buy.product.classical" bundle="${element_cards}"/>
<fmt:message var="basic" key="buy.product.basic" bundle="${element_cards}"/>

<fmt:message var="buy_action" key="buy.action.buy" bundle="${element_cards}"/>
<fmt:message var="price" key="buy.product.metal.price" bundle="${element_cards}"/>
<fmt:message var="units" key="buy.price.units" bundle="${element_cards}"/>

<c:set var="buy" value="controller?command=buy&product="/>
<c:set var="l_product_metal" value="metal"/>
<c:set var="l_product_rock" value="rock"/>
<c:set var="l_product_blues" value="blues"/>
<c:set var="l_product_jazz" value="jazz"/>
<c:set var="l_product_funk" value="funk"/>
<c:set var="l_product_ethnical" value="ethnical"/>
<c:set var="l_product_flamenco" value="flamenco"/>
<c:set var="l_product_pop" value="pop"/>
<c:set var="l_product_classic" value="classic"/>
<c:set var="l_product_basic" value="basic"/>


<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/cards.css">
    <title>Guitar lessons</title>
</head>
<body>
<%@include file="../header.jsp" %>
<div class="wrapper">
    <div class="container">
        <div class="continer-raw">
            <div class="continer-raw_card ">
                <div class="card_title">
                    ${metal}
                </div>
                <div class="card_price">
                    ${price} ${units}
                </div>
                <div class="card_btn">
                    <a href="controller?command=buy&genre=${l_product_metal}&price=${price}">${buy_action}</a>
                </div>
            </div>
            <div class="continer-raw_card">
                <div class="card_title">
                    ${rock}
                </div>
                <div class="card_price">
                    ${price} ${units}
                </div>
                <div class="card_btn">
                    <a href="controller?command=buy&genre=${l_product_rock}&price=${price}">${buy_action}</a>
                </div>
            </div>
            <div class="continer-raw_card">
                <div class="card_title">
                    ${blues}
                </div>
                <div class="card_price">
                    ${price} ${units}
                </div>

                <div class="card_btn">
                    <a href="controller?command=buy&genre=${l_product_blues}&price=${price}">${buy_action}</a>
                </div>


            </div>
        </div>
        <div class="continer-raw">
            <div class="continer-raw_card">
                <div class="card_title">
                    ${blues}
                </div>
                <div class="card_price">
                    ${price} ${units}
                </div>
                <div class="card_btn">
                    <a href="controller?command=buy&genre=${l_product_jazz}&price=${price}">${buy_action}</a>
                </div>
            </div>
            <div class="continer-raw_card">
                <div class="card_title">
                    ${funk}
                </div>
                <div class="card_price">
                    ${price} ${units}
                </div>
                <div class="card_btn">
                    <a href="controller?command=buy&genre=${l_product_funk}&price=${price}">${buy_action}</a>
                </div>
            </div>
            <div class="continer-raw_card">
                <div class="card_title">
                    ${ethnical}
                </div>
                <div class="card_price">
                    ${price} ${units}
                </div>
                <div class="card_btn">
                    <a href="controller?command=buy&genre=${l_product_ethnical}&price=${price}">${buy_action}</a>
                </div>
            </div>
        </div>
        <div class="continer-raw">
            <div class="continer-raw_card">
                <div class="card_title">
                    ${flamenco}
                </div>
                <div class="card_price">
                    ${price} ${units}
                </div>
                <div class="card_btn">
                    <a href="controller?command=buy&genre=${l_product_flamenco}&price=${price}">${buy_action}</a>
                </div>
            </div>
            <div class="continer-raw_card">
                <div class="card_title">
                    ${pop}
                </div>
                <div class="card_price">
                    ${price} ${units}
                </div>
                <div class="card_btn">
                    <a href="controller?command=buy&genre=${l_product_pop}&price=${price}">${buy_action}</a>
                </div>
            </div>
            <div class="continer-raw_card">
                <div class="card_title">
                    ${pop}
                </div>
                <div class="card_price">
                    ${price} ${units}
                </div>
                <div class="card_btn">
                    <a href="controller?command=buy&genre=${l_product_classic}&price=${price}">${buy_action}</a>
                </div>
            </div>
        </div>
        <div class="continer-raw">
            <div class="continer-raw_card last-card">
                <div class="card_title">
                    ${basic}
                </div>
                <div class="card_price">
                    ${price} ${units}
                </div>
                <div class="card_btn">
                    <a href="controller?command=buy&genre=${l_product_basic}&price=${price}">${buy_action}</a>
                </div>
            </div>

        </div>
    </div>
</div>
<%@include file="../footer.jsp" %>
</body>
</html>
