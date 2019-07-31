<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:url value="/mainPage.html" var="MainPageUrl"/>

<html>
<head>
    <c:choose>
        <c:when test="${not empty cookie.language.value}">
            <fmt:setLocale value="${cookie.language.value}"/>
        </c:when>
        <c:otherwise>
            <fmt:setLocale value="en"/>
        </c:otherwise>
    </c:choose>
    <fmt:setBundle basename="content"/>
    <%@ include file="/WEB-INF/jsp/bootstrap.jsp" %>
    <title>Error</title>
</head>

<body>
<div class="align-items-center">
    <div class="col" style="background:red">
        ${message}
    </div>
    <div class="col">
        <a href="${MainPageUrl}"><fmt:message key="toHomepage"/></a>
    </div>
</div>
</body>
</html>
