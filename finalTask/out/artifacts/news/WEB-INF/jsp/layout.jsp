<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib tagdir="/WEB-INF/tags" prefix="u"%>

<c:url value="/user_login.html" var="toLoginUrl"/>
<c:url value="/user_logout.html" var="toLogoutUrl"/>
<c:url value="/change_local.html" var="changeLocalUrl"/>
<c:url value="/news_category.html" var="newsCategoryUrl"/>
<c:url value="/personal_cabinet.html" var="personalCabinetUrl"/>
<c:url value="/mainPage.html" var="toMainPageUrl"/>


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

<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand" href="${toMainPageUrl}">#</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
           <li class="nav-item active">
                <a class="nav-link" href="${toMainPageUrl}"><fmt:message key="toHomepage"/><span class="sr-only">(current)</span></a>
            </li>
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    <fmt:message key="language"/>
                </a>
                <div class="dropdown-menu" aria-labelledby="navbarDropdownLang">
                    <a class="dropdown-item" href="${changeLocalUrl}?locale=en">en</a>
                    <div class="dropdown-divider"></div>
                    <a class="dropdown-item" href="${changeLocalUrl}?locale=ru">ru</a>
                </div>
            </li>
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownCategory" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    <fmt:message key="categories"/>
                </a>
                <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                    <a class="dropdown-item" href="${newsCategoryUrl}?category=world&currentPage=1"><fmt:message key="world"/></a>
                    <div class="dropdown-divider"></div>
                    <div class="dropdown-divider"></div>
                    <a class="dropdown-item" href="${newsCategoryUrl}?category=science&currentPage=1"><fmt:message key="science"/></a>
                    <div class="dropdown-divider"></div>
                    <div class="dropdown-divider"></div>
                    <a class="dropdown-item" href="${newsCategoryUrl}?category=technology&currentPage=1"><fmt:message key="technology"/></a>
                    <div class="dropdown-divider"></div>
                    <div class="dropdown-divider"></div>
                    <a class="dropdown-item" href="${newsCategoryUrl}?category=accidents&currentPage=1"><fmt:message key="accidents"/></a>
                    <div class="dropdown-divider"></div>
                    <div class="dropdown-divider"></div>
                    <a class="dropdown-item" href="${newsCategoryUrl}?category=politics&currentPage=1"><fmt:message key="politics"/></a>
                    <div class="dropdown-divider"></div>
                    <div class="dropdown-divider"></div>
                    <a class="dropdown-item" href="${newsCategoryUrl}?category=cars&currentPage=1"><fmt:message key="cars"/></a>
                </div>
            </li>
            <form class="form-inline my-2 my-lg-0">
                <input class="form-control mr-sm-2" type="search"  aria-label="Search">
                <button class="btn btn-outline-success my-2 my-sm-0" type="submit"><fmt:message key="search"/></button>
            </form>
        </ul>
        <c:choose>
            <c:when test="${not empty user}">
                <form class="form-inline px-2 my-lg-0" action="${personalCabinetUrl}" method="get">
                    <button class = "btn btn-link" type="submit"><fmt:message key="greeting"/>,&nbsp ${user.login}</button>
                </form>
                <form class="form-inline my-2 my-lg-0" action="${toLogoutUrl}" method="get">
                    <button class="btn btn-outline-success my-2 my-sm-0" type="submit"><fmt:message key="logout"/></button>
                </form>
            </c:when>
            <c:otherwise>
                <form class="form-inline my-2 my-lg-0" action="${toLoginUrl}" method="get">
                    <button class="btn btn-outline-success my-2 my-sm-0" type="submit"><fmt:message key="login/registration"/></button>
                </form>
            </c:otherwise>
        </c:choose>
    </div>
</nav>

