<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>



<c:url value="/write_article.html" var="writeArticleUrl"/>

<head>
    <title>C</title>
    <%@ include file="/WEB-INF/jsp/layout.jsp" %>
</head>


<div class="container">
<form action="${writeArticleUrl}" method="post" enctype="multipart/form-data">
    <div class="form-group">
        <label for="titleTextarea">Введите заголовок</label>
        <textarea class="form-control" id="titleTextarea" rows="7" name="articleTitle"></textarea>
    </div>
    <div class="form-group">
        <label for="textTextarea">Введите заголовок</label>
        <textarea class="form-control" id="textTextarea" rows="7" name="articleText"></textarea>
    </div>
    <div class="form-group">
        <label for="textTextarea">Введите авторскую категорию</label>
        <input class="form-control" id="categoryInput" name="markText"></input>
    </div>
    Загрузка изображения
    <input type="file" name="file" id="file">
    <button type="submit" class="btn btn-primary btn-lg">Отправить</button>
</form>
</div>
