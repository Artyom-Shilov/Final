<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:url value="/read_article_comments.html" var="readArticleCommentsUrl"/>

<html>
<head>
    <title>Articles</title>
    <style type="text/css">
        .jumbotron {
            padding-top: 6rem;
            padding-bottom: 6rem;
            margin-bottom: 0;
            background-color: #fff;
        }
        .article-details {
            text-align: left;
            color: gray;
            font: 13px Arial;

        }
        button {
            margin:auto;
            display:block;
        }
    </style>
</head>

<body>
<%@ include file="/WEB-INF/jsp/layout.jsp" %>
<section class="jumbotron text-center">
    <div class="container">
        <h2 class="jumbotron-heading">${article.title}</h2>
    </div>
    <br>
    <div class="container article-details">
        <p>${writerLogin}  ${article.creationDate}</p>
    </div>
    <div class="container">
        <p align="justify">${article.text}</p>
    </div>
    <br>
    <form action="${readArticleCommentsUrl}" method="get">
        <input type="hidden" name="articleId" value="${article.id}">
        <input type="hidden" name="currentPage" value="1">
        <button type="submit" class="btn btn-primary btn-lg">Комментарии(${commentsNumber})</button>
    </form>
</section>
</body>
</html>