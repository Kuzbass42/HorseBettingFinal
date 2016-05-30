<%@ page pageEncoding="utf-8" contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="locale" var="loc"/>
<fmt:message bundle="${loc}" key="local.en_button" var="en_button"/>
<fmt:message bundle="${loc}" key="local.ru_button" var="ru_button"/>
<fmt:message bundle="${loc}" key="local.mainTitle" var="mainTitle"/>
<fmt:message bundle="${loc}" key="local.welcome" var="welcome"/>
<fmt:message bundle="${loc}" key="local.message" var="message"/>
<fmt:message bundle="${loc}" key="local.login" var="login"/>
<fmt:message bundle="${loc}" key="local.logout" var="logout"/>
<fmt:message bundle="${loc}" key="local.register" var="register"/>
<fmt:message bundle="${loc}" key="local.races" var="races"/>
<fmt:message bundle="${loc}" key="local.horses" var="horses"/>
<fmt:message bundle="${loc}" key="local.odds" var="odds"/>
<fmt:message bundle="${loc}" key="local.users.myinfo" var="myinfo"/>

<html>
<head>
    <title>${mainTitle}</title>
</head>
<body>
<table>
    <th>
    <form action="/Localizator" method="post">
        <input type="hidden" name="local" value="en"/>
        <input type="submit" value="${en_button}"/>
    </form>
    </th>
    <th>
    <form action="/Localizator" method="post">
        <input type="hidden" name="local" value="ru"/>
        <input type="submit" value="${ru_button}"/>
    </form>
    </th>
    <th>
    <c:choose>
        <c:when test="${!requestScope.isLoggedIn}">
        <table>
            <th>
            <form action="/auth" method="get">
                <input type="hidden" name="login" value="${true}"/>
                <input type="submit" value="${login}"/>
            </form>
            </th>
            <th>
            <form action="/auth" method="get">
                <input type="hidden" name="reg" value="${true}"/>
                <input type="submit" value="${register}"/>
            </form>
            </th>
        </table>
        </c:when>
        <c:otherwise>
            <form action="/auth" method="post">
                <input type="hidden" name="logout" value="${true}"/>
                <input type="submit" value="${logout}"/>
            </form>
        </c:otherwise>
    </c:choose>
    <th>
</table>

    <h1>${welcome}</h1> <br/>
    <p>${message}!</p> <br/>


    <li><a href="/users">${myinfo}</a></li>
    <li><a href="/races">${races}</a></li>
    <li><a href="/horses">${horses}</a></li>
    <li><a href="/odds">${odds}</a></li>

</body>
</html>
