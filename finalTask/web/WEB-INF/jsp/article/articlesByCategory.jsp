<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:url value="/news_category.html" var="newsCategoryUrl"/>
<c:url value="/read_article.html" var="readArticleUrl"/>

<html>
<head>
    <title>Articles</title>
    <style>
        #category {
            text-align: center;
            margin-bottom: 30px;
            margin-top: 30px;
        }
        #article {
            font-size: 18pt;
            margin-bottom: 30px;
            margin-top: 30px;
        }

    </style>
</head>

<body>
<%@ include file="/WEB-INF/jsp/layout.jsp" %>
<h3 id = "category"><fmt:message key="newsByCategory"/><fmt:message key="${category}"/></h3>
    <div class="container justify-content-center">
    <c:forEach items="${newsByCategory}" var="item">
        <div class="row" id = "article">
        <a href="${readArticleUrl}?id=${item.id}">${item.title}<br></a>
        </div>
    </c:forEach>
    </div>

    <nav aria-label="Navigation">
    <ul class="pagination pagination justify-content-center">
    <c:if test="${currentPage != 1}">
        <li class="page-item"><a class="page-link"
                                 href="${newsCategoryUrl}?currentPage=${currentPage-1}&category=${category}"><fmt:message
                key="previous"/></a>
        </li>
    </c:if>

    <c:forEach begin="1" end="${numberOfPages}" var="i">
        <c:choose>
            <c:when test="${currentPage eq i}">
                <li class="page-item active"><a class="page-link">
                        ${i} <span class="sr-only">(current)</span></a>
                </li>
            </c:when>
            <c:otherwise>
                <li class="page-item"><a class="page-link"
                                         href="${newsCategoryUrl}?currentPage=${i}&category=${category}">${i}</a>
                </li>
            </c:otherwise>
        </c:choose>
    </c:forEach>

    <c:if test="${currentPage lt numberOfPages}">
        <li class="page-item"><a class="page-link"
                                 href="${newsCategoryUrl}?currentPage=${currentPage+1}&category=${category}"><fmt:message
                key="next"/></a>
        </li>
    </c:if>
    </ul>
    </nav>
</body>
</html>