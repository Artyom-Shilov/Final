
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:url value="/user_register.html" var="RegisterActionUrl"/>


<head>
    <%@ include file="/WEB-INF/jsp/layout.jsp" %>
    <title><fmt:message key="registration"/></title>

    <style type="text/css">
        .login-form {
            width: 340px;
            margin: 50px auto;
        }
        .login-form form {
            margin-bottom: 15px;
            background: #f7f7f7;
            box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3);
            padding: 30px;
        }
        .login-form h2 {
            margin: 0 0 15px;
        }
        .form-control, .btn {
            min-height: 38px;
            border-radius: 2px;
        }
        .btn {
            font-size: 15px;
            font-weight: bold;
        }
    </style>
</head>


<div class="login-form">
    <form action="${RegisterActionUrl}" method="post">
        <div class="form-group">
            <input type="text" class="form-control" placeholder="<fmt:message key="login"/>" required="required" name="login">
        </div>
        <div class="form-group">
            <input type="password" class="form-control" placeholder="<fmt:message key="password"/>" required="required" name="password">
        </div>
        <div class="form-group">
            <input type="text" class="form-control" placeholder="<fmt:message key="name"/>" required="required" name="name">
        </div>
        <div class="form-group">
            <input type="text" class="form-control" placeholder="<fmt:message key="surname"/>" required="required" name="surname">
        </div>
        <div class="form-group">
            <input type="date" class="form-control" placeholder="Birthday" required="required" name="birthday">
        </div>
        <div class="form-group">
            <button type="submit" class="btn btn-primary btn-block"><fmt:message key="registration"/></button>
        </div>
    </form>
</div>



