<%@ page pageEncoding="utf-8" contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="mt" uri="/WEB-INF/mytag.tld" %>

<jsp:useBean id="oddList" type="java.util.ArrayList" scope="request"/>

<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="locale" var="loc"/>
<fmt:message bundle="${loc}" key="local.odds.title" var="title"/>
<fmt:message bundle="${loc}" key="local.odds.transaction" var="transaction"/>
<fmt:message bundle="${loc}" key="local.odds.date" var="date"/>
<fmt:message bundle="${loc}" key="local.odds.odds" var="odds"/>
<fmt:message bundle="${loc}" key="local.odds.stake" var="stake"/>
<fmt:message bundle="${loc}" key="local.odds.winnings" var="winnings"/>
<fmt:message bundle="${loc}" key="local.odds.pick" var="pick"/>
<fmt:message bundle="${loc}" key="local.odds.result" var="result"/>
<fmt:message bundle="${loc}" key="local.odds.username" var="username"/>
<fmt:message bundle="${loc}" key="local.main" var="main"/>
<html>
<head>
    <title>${title}</title>
</head>
<body>
    <table style="border: 1px solid #000;" cellpadding="5" cellspacing="5">
        <tr>
            <th>${transaction}</th>
            <th>${date}</th>
            <th>${username}</th>
            <th>${stake}</th>
            <th>${odds}</th>
            <th>${pick}</th>
            <th>${result}</th>
            <th>${winnings}</th>
        </tr>

        <c:forEach var="odd" items="${oddList}">
            <tr>
                <td>${odd.getTransId()}</td>
                <td>${odd.getOddFormatDate()}</td>
                <td>${odd.user.getUserName()}</td>
                <td>${odd.getAmount()}</td>
                <th>${odd.raceLine.getOdd()}</th>
                <td>${odd.raceLine.getHorseName()}</td>
                <td>${odd.race.getHorseWinnerName()}</td>
                <td>
                    <mt:winamount winHorse="${odd.race.horseWinner.getHorseId()}" pickHorse="${odd.raceLine.horse.getHorseId()}"
                                        stake="${odd.getAmount()}" odd="${odd.raceLine.getOdd()}"/>
                </td>
            </tr>
        </c:forEach>
    </table>

    <br/>
    <li><a href="/index.jsp">${main}</a></li>
</body>
</html>
