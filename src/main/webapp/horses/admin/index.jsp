<%@ page pageEncoding="utf-8" contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<jsp:useBean id="horseList" type="java.util.ArrayList" scope="request"/>

<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="locale" var="loc"/>
<fmt:message bundle="${loc}" key="local.horses.id" var="horseId"/>
<fmt:message bundle="${loc}" key="local.horses.horsename" var="horsename"/>
<fmt:message bundle="${loc}" key="local.horses.color" var="color"/>
<fmt:message bundle="${loc}" key="local.horses.horsesTitle" var="horsesTitle"/>
<fmt:message bundle="${loc}" key="local.edit" var="edit"/>
<fmt:message bundle="${loc}" key="local.new" var="addnew"/>
<fmt:message bundle="${loc}" key="local.main" var="main"/>

<html>
<head>
    <title>${horsesTitle}</title>
</head>
<body>
    <form action="/horses/admin/edit" method="get">
        <input type="hidden" name="newHorse" value="${true}"/>
        <input type="submit" value="${addnew}"/>
    </form>

    <table style="border: 1px solid #000;" cellpadding="5" cellspacing="5">
        <tr>
            <th>${horseId}</th>
            <th>${horsename}</th>
            <th>${color}</th>
            <th></th>
        </tr>
        <c:forEach var="horse" items="${horseList}">
            <tr>
                <td>
                    ${horse.getHorseId()}
                </td>
                <td>
                    ${horse.getHorseName()}
                </td>
                <td>
                    ${horse.getColor()}
                </td>
                <td>
                    <form action="/horses/admin/edit" method="get">
                        <input type="hidden" name="newHorse" value="${false}"/>
                        <input type="hidden" name="horseId" value="${horse.getHorseId()}"/>
                        <input type="submit" value="${edit}"/>
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>

    <br/>
    <li><a href="/index.jsp">${main}</a></li>
</body>
</html>
