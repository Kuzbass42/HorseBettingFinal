<%@ page pageEncoding="utf-8" contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="locale" var="loc"/>
<fmt:message bundle="${loc}" key="local.horses.id" var="horseId"/>
<fmt:message bundle="${loc}" key="local.horses.horsename" var="horsename"/>
<fmt:message bundle="${loc}" key="local.horses.color" var="color"/>
<fmt:message bundle="${loc}" key="local.horses.horsesTitle" var="horsesTitle"/>
<fmt:message bundle="${loc}" key="local.edit" var="edit"/>
<fmt:message bundle="${loc}" key="local.editing" var="editing"/>
<fmt:message bundle="${loc}" key="local.delete" var="delete"/>
<fmt:message bundle="${loc}" key="local.save" var="save"/>
<fmt:message bundle="${loc}" key="local.horses" var="horses"/>
<fmt:message bundle="${loc}" key="local.main" var="main"/>

<c:set var="newHorse" scope="page" value="${requestScope.newHorse}"/>
<c:if test="${!newHorse}">
    <jsp:useBean id="horse" type="model.view.HorseView" scope="request"/>
</c:if>
<html>
<head>
    <title>${editing}</title>
</head>
<body>
    <form action="/horses/admin/edit" method="post">
        <table style="border: 1px solid #000;" cellpadding="5" cellspacing="5">
            <tr>
                <th>${horseId}</th>
                <th>${horsename}</th>
                <th>${color}</th>
            </tr>
                <tr>
                    <td>
                        <c:if test="${!newHorse}">
                            ${horse.getHorseId()}
                        </c:if>
                    </td>
                    <td>
                        <c:if test="${!newHorse}">
                            ${horse.getHorseName()}<br/>
                        </c:if>
                            <input type="text" name="horseName" title="horseName" required/>
                    </td>
                    <td>
                        <c:if test="${!newHorse}">
                            ${horse.getColor()}<br/>
                        </c:if>
                            <input type="text" name="color" title="color"/>
                    </td>
                </tr>
        </table>
        <input type="hidden" name="newHorse" value="${newHorse}"/>
        <input type="hidden" name="horseId" value="${horse.getHorseId()}"/>
        <input type="submit" name="save" value="${save}"/>
    </form>

    <c:if test="${!newHorse}">
        <form action="/horses/admin/edit" method="post">
            <input type="hidden" name="horseId" value="${horse.getHorseId()}"/>
            <input type="hidden" name="delete" value="${true}">
            <input type="submit" value="${delete}" />
        </form>
    </c:if>

    <br/>
    <li><a href="/horses">${horses}</a></li>
    <li><a href="/index.jsp">${main}</a></li>
</body>
</html>
