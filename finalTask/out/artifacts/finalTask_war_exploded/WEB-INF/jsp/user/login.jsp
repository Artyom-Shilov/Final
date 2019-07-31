
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:url value="/user_login.html" var="LoginActionUrl"/>
<c:url value="/user_register.html" var = "RegisterActionUrl"/>


<head>
    <%@ include file="/WEB-INF/jsp/layout.jsp" %>
    <title><fmt:message key="login"/></title>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <script type="text/javascript">
        <%@include file="/WEB-INF/js/loginFormValidator.js"%>
    </script>
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
    <form action="${LoginActionUrl}" method="post" id = loginForm>
        <div class="form-group">
            <input type="text" id = "login" name = "login" class="form-control" placeholder=<fmt:message key="login"/>>
        </div>
        <div class="form-group">
            <input type="password" id = "password" name = "password" class="form-control" placeholder=<fmt:message key="password"/>>
        </div>
        <div class="form-group">
            <button type="submit" class="btn btn-primary btn-block"><fmt:message key="toLogin"/></button>
        </div>
        <div class="clearfix">
            <a href="#" class="pull-right"><fmt:message key="forgotPassword"/></a>
        </div>
    </form>
    <p class="text-center"><a href="${RegisterActionUrl}"><fmt:message key="createAccount"/></a></p>
</div>






