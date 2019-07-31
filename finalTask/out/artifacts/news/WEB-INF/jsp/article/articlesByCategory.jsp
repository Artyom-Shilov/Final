<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:url value="/news_category.html" var="newsCategoryUrl"/>
<c:url value="/read_article.html" var="readArticleUrl"/>

<head>
    <title>Articles</title>
    <%@ include file="/WEB-INF/jsp/layout.jsp" %>
</head>

<c:forEach items="${newsByCategory}" var="item">
    <a href="${readArticleUrl}?id=${item.id}">${item.title}<br>
</c:forEach>

<nav aria-label="Navigation">
    <ul class="pagination">
        <c:if test="${currentPage != 1}">
            <li class="page-item"><a class="page-link" href="${newsCategoryUrl}?currentPage=${currentPage-1}&category=${category}">Previous</a>
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
                    <li class="page-item"><a class="page-link" href="${newsCategoryUrl}?currentPage=${i}&category=${category}">${i}</a>
                    </li>
                </c:otherwise>
            </c:choose>
        </c:forEach>

        <c:if test="${currentPage lt numberOfPages}">
            <li class="page-item"><a class="page-link" href="${newsCategoryUrl}?currentPage=${currentPage+1}&category=${category}">Next</a>
            </li>
        </c:if>
    </ul>
</nav>

