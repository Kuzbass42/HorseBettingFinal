<%@ page pageEncoding="utf-8" contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="locale" var="loc"/>
<fmt:message bundle="${loc}" key="local.username" var="username"/>
<fmt:message bundle="${loc}" key="local.password" var="password"/>
<fmt:message bundle="${loc}" key="local.login" var="login"/>
<fmt:message bundle="${loc}" key="local.main" var="main"/>
<head>
    <html>
    <head>
        <title>${login}</title>
    </head>
<body>
<form method="post" action="/auth">
    <table border="0" cellspacing="5">
        <tr>
            <th align="right">${username}:</th>
            <td align="left"><input type="text" name="j_username" required></td>
        </tr>
        <tr>
            <th align="right">${password}:</th>
            <td align="left"><input type="password" name="j_password" required></td>
        </tr>
        <tr>
            <input type="hidden" name="login" value="${true}"/>
            <td align="right"><input type="submit" value="${login}"></td>
        </tr>
    </table>

    <br/>
    <li><a href="/index.jsp">${main}</a></li>
</form>
</body>
</html>
