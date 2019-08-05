
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:url value="/write_article.html" var="writeArticleUrl"/>

<html>
<head>
    <title>Cabinet</title>
</head>
<body>
<%@ include file="/WEB-INF/jsp/layout.jsp" %>
<a href="${writeArticleUrl}">
<div>Написать статью</div>
</a>
</body>
</html>

