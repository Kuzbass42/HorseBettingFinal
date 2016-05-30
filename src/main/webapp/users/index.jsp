<%@ page pageEncoding="utf-8" contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:useBean id="user" type="model.view.UserView" scope="request"/>

<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="locale" var="loc"/>
<fmt:message bundle="${loc}" key="local.users.title" var="title"/>
<fmt:message bundle="${loc}" key="local.users.welcomemessage" var="welcomemessage"/>
<fmt:message bundle="${loc}" key="local.users.yourbalance" var="yourbalance"/>
<fmt:message bundle="${loc}" key="local.users.yourname" var="yourname"/>
<fmt:message bundle="${loc}" key="local.users.deposit" var="deposit"/>
<fmt:message bundle="${loc}" key="local.users.bankcard" var="bankcard"/>
<fmt:message bundle="${loc}" key="local.users.depamount" var="depamount"/>
<fmt:message bundle="${loc}" key="local.users.lowbalance" var="lowbalance"/>
<fmt:message bundle="${loc}" key="local.main" var="main"/>
<fmt:message bundle="${loc}" key="local.races" var="races"/>
<fmt:message bundle="${loc}" key="local.horses" var="horses"/>
<fmt:message bundle="${loc}" key="local.odds" var="odds"/>

<html>
<head>
    <title>${title}</title>
</head>
<body>
    <h1>${welcomemessage}, ${user.getUserName()}</h1>

    <p>${yourname}: ${user.getUserName()}</p>
    <p>${yourbalance}: ${user.getBalance()}</p>

    <c:if test="${requestScope.lowbalance}">
        <p>${lowbalance} </p><br/>
    </c:if>

    <br/>
    <form action="/deposit" method="post">
        <table border="0" cellspacing="5">
            <tr>
                <th align="right">${bankcard}:</th>
                <td align="left"><input type="text" pattern="\d+" required></td>
            </tr>
            <tr>
                <th align="right">${depamount}:</th>
                <td align="left"><input type="text" name="depamount" title="0.00" pattern="\d+(\.\d{2})?" required></td>
            </tr>
            <tr>
                <td align="right"><input type="submit" value="${deposit}"/></td>
            </tr>
        </table>
    </form>

    <li><a href="/index.jsp">${main}</a></li>
</body>
</html>
