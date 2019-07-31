<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

    <H2> UserList </H2>

    <c:url value="/user/list.html" var="userListUrl"/>

    <FORM action="userListUri" method="get">
        <BUTTON type="submit">UserList</BUTTON>
    </FORM>

    <TABLE>
        <TR>
            <TH>Имя пользователя</TH>
            <TH>Роль</TH>
        </TR>
        <c:forEach items="${users}" var="user">
            <TR>
                <TD>
                        ${user.login}
                </TD>
                <TD>${user.role.name}</TD>
            </TR>
        </c:forEach>
    </TABLE>
