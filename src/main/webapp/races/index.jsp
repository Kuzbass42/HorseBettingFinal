<%@ page pageEncoding="utf-8" contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<jsp:useBean id="raceList" type="java.util.ArrayList" scope="request"/>
<jsp:useBean id="user" type="model.view.UserView" scope="request"/>

<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="locale" var="loc"/>
<fmt:message bundle="${loc}" key="local.races.title" var="title"/>
<fmt:message bundle="${loc}" key="local.races.name" var="name"/>
<fmt:message bundle="${loc}" key="local.races.date" var="date"/>
<fmt:message bundle="${loc}" key="local.races.winner" var="winner"/>

<fmt:message bundle="${loc}" key="local.races.raceline.horse" var="horse"/>
<fmt:message bundle="${loc}" key="local.races.raceline.odd" var="odd"/>
<fmt:message bundle="${loc}" key="local.races.raceline.bet" var="bet"/>
<fmt:message bundle="${loc}" key="local.races.raceline.placebet" var="placebet"/>

<fmt:message bundle="${loc}" key="local.edit" var="edit"/>
<fmt:message bundle="${loc}" key="local.main" var="main"/>

<html>
<head>
    <title>${title}</title>
</head>
<body>

<li><a href="/index.jsp">${main}</a></li>

<c:forEach var="race" items="${raceList}">
    <table border="0" cellspacing="5">
        <tr>
            <th align="right">${name}:</th>
            <td align="left">${race.getRaceName()}</td>

            <th align="right">${date}:</th>
            <td align="left">${race.getRaceFormatDate()}</td>
        </tr>
    </table>

    <c:if test="${race.hasRaceLine()}">
        <table style="border: 1px solid #000;" cellpadding="5" cellspacing="5">
            <tr>
                <th>${horse}</th>
                <th>${odd}</th>
                <th>${bet}</th>
                <th></th>
            </tr>

            <c:forEach var="raceLine" items="${race.getRaceLineList()}">
                <tr>
                    <td>${raceLine.getHorseName()}</td>
                    <td>${raceLine.getOdd()}</td>
                    <td>
                        <form action="/placebet" method="post">
                            <input type="hidden" name="raceid" value="${raceLine.getRaceId()}"/>
                            <input type="hidden" name="linenum" value="${raceLine.getLineNum()}"/>
                            <input type="text" name="bet"  title="0.00" pattern="\d+(\.\d{2})?" required/>
                            <input type="submit" value="${placebet}"/>
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </c:if>
</c:forEach>

</body>
</html>
