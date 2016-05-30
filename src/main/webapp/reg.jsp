<%@ page pageEncoding="utf-8" contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="locale" var="loc"/>
<fmt:message bundle="${loc}" key="local.username" var="username"/>
<fmt:message bundle="${loc}" key="local.password" var="password"/>
<fmt:message bundle="${loc}" key="local.name" var="name"/>
<fmt:message bundle="${loc}" key="local.send" var="send"/>
<fmt:message bundle="${loc}" key="local.regmessage" var="regmessage"/>
<fmt:message bundle="${loc}" key="local.regerror" var="regerror"/>
<fmt:message bundle="${loc}" key="local.register" var="register"/>
<fmt:message bundle="${loc}" key="local.main" var="main"/>

<html>
<head>
    <title>${register}</title>
</head>
<body>
    <c:if test="${requestScope.error}">
        ${regerror} <br/>
    </c:if>

    <h1>${regmessage}</h1>

    <form action="/auth" method="post">
        <table border="0" cellspacing="5">
            <tr>
                <th align="right">${name}:</th>
                <td align="left"><input name="userName" type="text" value="${requestScope.username}" required></td>
            </tr>
            <tr>
                <th align="right">${username}:</th>
                <td align="left"><input name="j_username" type="text" value="${requestScope.j_username}" required/></td>
            </tr>
            <tr>
                <th align="right">${password}:</th>
                <td align="left"><input name="j_password" type="password" required/></td>
            </tr>
            <tr>
                <th></th>
                <td align="left">
                <input type="hidden" name="registered" value="${true}"/>
                <input type="submit" value="${send}"/>
                </td>
            </tr>
        </table>

        <br/>
        <li><a href="/index.jsp">${main}</a></li>
    </form>
</body>
</html>
