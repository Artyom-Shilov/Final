<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:url value="/read_article_comments.html" var="readArticleCommentsUrl"/>
<c:url value="/write_comment.html" var="writeCommentUrl"/>
<c:url value="/rate_comment.html" var="rateCommentUrl"/>

<head>
    <title>C</title>
    <%@ include file="/WEB-INF/jsp/layout.jsp" %>
    <script src="https://kit.fontawesome.com/78b1ec37e4.js"></script>
    <style>
        #title{
            text-align: center;
            margin-bottom: 30px;
            margin-top: 30px;
        }

        #mainComment{
            flex: 1;
            background: white;
            border: 1px solid gray;
        }

        #qComment{
            flex: 1;
            background: white;
            border: 1px solid gray;
        }


        .container {
            margin-bottom: 10px;
        }

        .fa-plus, .plus {
            color: green;
        }
        .fa-minus, .minus {
            color: red;
        }
    </style>
</head>

<h3 id = "title">${title}</h3>

<div class="container">
    <div class="row">
        <div class="col-md-4">
            <c:choose>
                <c:when test="${not empty user}">
                    <a href="${writeCommentUrl}?article=${articleId}">Оставить комментарий</a>
                </c:when>
                <c:otherwise>
                    Чтобы оставить комментарий, необходимо войти или зарегистрироваться
                </c:otherwise>
            </c:choose>
        </div>
    </div>


    <div class="container">
        <c:forEach var="main" items="${comments}">
        <div class="row">
            <div class="col-md-4 border-right-0" id="mainComment">
                    ${main.key.commentatorLogin}<br>
                    ${main.key.creationDate}<br>
                <c:choose>
                    <c:when test="${not empty user}">
                        <a href="${rateCommentUrl}?rComment=${main.key.id}&character=plus">
                            <i class="fas fa-plus"></i>
                        </a>
                        <span class="plus">${main.key.plusNumber}</span><br>
                        <a href="${rateCommentUrl}?rComment=${main.key.id}&character=minus">
                            <i class="fas fa-minus"></i>
                            <span class="minus">${main.key.minusNumber}</span>
                        </a> <br>
                    </c:when>
                    <c:otherwise>
                        <a href="#"><i class="fas fa-plus"></i></a>
                        <span class="plus">${main.key.plusNumber}</span><br>
                        <a href="#"><i class="fas fa-minus"></i></a>
                        <span class="minus">${main.key.minusNumber}</span>
                    </c:otherwise>
                </c:choose>
                <c:if test="${not empty user}">
                    <a href="${writeCommentUrl}?qComment=${main.key.id}&article=${articleId}">Ответить</a>
                </c:if>
            </div>
            <div class="col-md-6 border-left-0" id="qComment">
                <div class="col">${main.key.text}<br>
                    <c:choose>
                    <c:when test="${not empty main.value}">
                    Ответ на:<br>
                </div>
                <div class="col border bg-light">
                    <c:forEach var="comment" items="${main.value}">
                        <div class="col-md-8">
                                ${comment.commentatorLogin}<br>
                                ${comment.creationDate}<br>
                                ${comment.text}
                        </div>
                    </c:forEach><br>
                </div>
                </c:when>
                <c:otherwise>
            </div>
            </c:otherwise>
            </c:choose>
        </div>
    </div>
    </c:forEach>
</div>

<nav aria-label="Navigation">
    <ul class="pagination justify-content-center">
        <c:if test="${currentPage != 1}">
            <li class="page-item"><a class="page-link"
                                     href="${readArticleCommentsUrl}?currentPage=${currentPage-1}&articleId=${articleId}">Previous</a>
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
                                             href="${readArticleCommentsUrl}?currentPage=${i}&articleId=${articleId}">${i}</a>
                    </li>
                </c:otherwise>
            </c:choose>
        </c:forEach>
        <c:if test="${currentPage lt numberOfPages}">
            <li class="page-item"><a class="page-link"
                                     href="${readArticleCommentsUrl}?currentPage=${currentPage+1}&articleId=${articleId}">Next</a>
            </li>
        </c:if>
    </ul>
</nav>


