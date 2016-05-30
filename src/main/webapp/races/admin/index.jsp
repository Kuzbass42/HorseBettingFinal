<%@ page pageEncoding="utf-8" contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<jsp:useBean id="raceList" type="java.util.ArrayList" scope="request"/>

<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="locale" var="loc"/>
<fmt:message bundle="${loc}" key="local.races.title" var="title"/>
<fmt:message bundle="${loc}" key="local.races.name" var="name"/>
<fmt:message bundle="${loc}" key="local.races.date" var="date"/>
<fmt:message bundle="${loc}" key="local.races.winner" var="winner"/>

<fmt:message bundle="${loc}" key="local.races.raceline.horse" var="horse"/>
<fmt:message bundle="${loc}" key="local.races.raceline.odd" var="odd"/>

<fmt:message bundle="${loc}" key="local.edit" var="edit"/>
<fmt:message bundle="${loc}" key="local.new" var="addnew"/>
<fmt:message bundle="${loc}" key="local.main" var="main"/>

<html>
<head>
    <title>${title}</title>
</head>
<body>

<li><a href="/index.jsp">${main}</a></li> <br/>

<form action="/races/admin/edit" method="get">
    <input type="hidden" name="newrace" value="${true}"/>
    <input type="submit" value="${addnew}"/>
</form>

<c:forEach var="race" items="${raceList}">
    <c:set var="editEnable" value="${race.getHorseWinner().getHorseId() == 0}" scope="page"/>
    <table border="0" cellspacing="5">
        <tr>
            <th>
                <form action="/races/admin/edit" method="get">
                    <input type="hidden" name="newrace" value="${false}"/>
                    <input type="hidden" name="raceid" value="${race.getRaceId()}"/>
                    <!--<input type="submit" value="${edit}"/>-->
                    <c:choose>
                        <c:when test="${editEnable}">
                            <input type="submit" value="${edit}"/>
                        </c:when>
                        <c:otherwise>
                            <input type="submit" value="${edit}" disabled/>
                        </c:otherwise>
                    </c:choose>
                </form>
            </th>

            <th align="right">${name}:</th>
            <td align="left">${race.getRaceName()}</td>

            <th align="right">${date}:</th>
            <td align="left">${race.getRaceFormatDate()}</td>

            <th align="right">${winner}:</th>
            <td align="left"> ${race.getHorseWinnerName()}</td>
        </tr>
    </table>

    <c:if test="${race.hasRaceLine()}">
        <table style="border: 1px solid #000;" cellpadding="5" cellspacing="5">
            <tr>
                <th>${horse}</th>
                <th>${odd}</th>
            </tr>

            <c:forEach var="raceLine" items="${race.getRaceLineList()}">
                <tr>
                    <td>${raceLine.getHorseName()}</td>
                    <td>${raceLine.getOdd()}</td>
                </tr>
            </c:forEach>
        </table>
    </c:if>
</c:forEach>

</body>
</html>
