<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:url value="/js" var="javascriptUrl"/>
<c:url value="/write_article.html" var="writeArticleUrl"/>

<html>
<head>
    <title>WriteArticle</title>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <c:choose>
        <c:when test="${not empty cookie.language.value}">
            <c:choose>
                <c:when test="${cookie.language.value == 'ru'}">
                    <script type="text/javascript" src="${javascriptUrl}/articleWriteFormValidation_ru.js"></script>
                </c:when>
        <c:otherwise>
            <script type="text/javascript" src="${javascriptUrl}/articleWriteFormValidation_en.js"></script>
        </c:otherwise>
            </c:choose>
    </c:when>
            <c:otherwise>
                <script type="text/javascript" src="${javascriptUrl}/articleWriteFormValidation_en.js"></script>
            </c:otherwise>
    </c:choose>
    <style>
        #writeArticleSubmitButton {
            margin-bottom: 30px;
            margin-top: 30px;
        }
        #writeArticleForm {
            margin-top: 30px;
        }

    </style>
</head>

<body>
<%@ include file="/WEB-INF/jsp/layout.jsp" %>
<div class="container">
<form action="${writeArticleUrl}" method="post" enctype="multipart/form-data" id="writeArticleForm">
    <div class="form-group col-md-6">
        <label for="titleTextarea"><fmt:message key="enterTitle"/></label>
        <textarea class="form-control" id="titleTextarea" rows="7" name="articleTitle"></textarea>
    </div>
    <div class="form-group col-md-6">
        <label for="textTextarea"><fmt:message key="enterText"/></label>
        <textarea class="form-control" id="textTextarea" rows="7" name="articleText"></textarea>
    </div>
    <div class="form-group col-md-6">
        <select id="categorySelect" name="category">
            <option disabled selected><fmt:message key="chooseCategory"/></option>
            <c:forEach items="${categories}" var="item">
                <option>${item}</option>
            </c:forEach>
        </select>
    </div>
    <div class="form-group col-md-6">
        <label for="markInput"><fmt:message key="enterAuthorCategory"/></label>
        <input class="form-control" id="markInput" name="markText"/>
    </div>
    <div class="form-group col-md-6">
    <label for="file"><fmt:message key="uploadImage"/></label>
    <input type="file" name="file" id="file"><br>
    </div>
    <button type="submit" class="btn btn-primary btn-lg" id="writeArticleSubmitButton"><fmt:message key="submit"/></button>
</form>
</div>
</body>
</html>