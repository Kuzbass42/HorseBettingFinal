<%@ page pageEncoding="utf-8" contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:useBean id="horseList" type="java.util.ArrayList" scope="request"/>

<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="locale" var="loc"/>
<fmt:message bundle="${loc}" key="local.horses.horsename" var="horsename"/>
<fmt:message bundle="${loc}" key="local.horses.color" var="color"/>
<fmt:message bundle="${loc}" key="local.horses.horsesTitle" var="horsesTitle"/>
<fmt:message bundle="${loc}" key="local.main" var="main"/>
<html>
<head>
    <title>${horsesTitle}</title>
</head>
<body>
    <table style="border: 1px solid #000;" cellpadding="5" cellspacing="5">
        <tr>
            <th>${horsename}</th>
            <th>${color}</th>
        </tr>

        <c:forEach var="horse" items="${horseList}">
            <tr>
                <td>${horse.getHorseName()}</td>
                <td>${horse.getColor()}</td>
            </tr>
        </c:forEach>
    </table>

    <br/>
    <li><a href="/index.jsp">${main}</a></li>
</body>
</html>
