<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>



<c:url value="/write_comment.html" var="writeCommentUrl"/>

<head>
    <title>C</title>
<%@ include file="/WEB-INF/jsp/layout.jsp" %>
</head>


<form action="${writeCommentUrl}" method="post">
    <div class="form-group">
        <label for="formControlTextarea">Введите комментарий</label>
        <textarea class="form-control" id="formControlTextarea" rows="7" name="commentText"></textarea>
    </div>
    <button type="submit" class="btn btn-primary btn-lg">Отправить</button>
</form>
