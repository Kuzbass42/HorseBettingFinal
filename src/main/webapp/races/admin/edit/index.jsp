<%@ page pageEncoding="utf-8" contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="C" uri="http://java.sun.com/jsp/jstl/core" %>

<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="locale" var="loc"/>
<fmt:message bundle="${loc}" key="local.races.title" var="title"/>
<fmt:message bundle="${loc}" key="local.races" var="races"/>
<fmt:message bundle="${loc}" key="local.races.id" var="id"/>
<fmt:message bundle="${loc}" key="local.races.name" var="name"/>
<fmt:message bundle="${loc}" key="local.races.date" var="date"/>
<fmt:message bundle="${loc}" key="local.races.winner" var="winner"/>
<fmt:message bundle="${loc}" key="local.races.addline" var="addline"/>
<fmt:message bundle="${loc}" key="local.races.choosehorse" var="choosehorse"/>

<fmt:message bundle="${loc}" key="local.races.raceline.horse" var="horse"/>
<fmt:message bundle="${loc}" key="local.races.raceline.odd" var="odd"/>

<fmt:message bundle="${loc}" key="local.delete" var="delete"/>
<fmt:message bundle="${loc}" key="local.save" var="save"/>
<fmt:message bundle="${loc}" key="local.edit" var="edit"/>
<fmt:message bundle="${loc}" key="local.editing" var="editing"/>
<fmt:message bundle="${loc}" key="local.main" var="main"/>

<c:set var="newrace" scope="page" value="${requestScope.newrace}"/>
<c:if test="${!newrace}">
    <jsp:useBean id="race" type="model.view.RaceView" scope="request"/>
    <jsp:useBean id="winninghorselist" type="java.util.ArrayList" scope="request"/>
</c:if>

<jsp:useBean id="horselist" type="java.util.ArrayList" scope="request"/>

<html>
<head>
    <title>${editing}</title>
</head>
<body>
<form action="/races/admin/edit" method="post">
    <table style="border: 1px solid #000;" cellpadding="5" cellspacing="5">
        <tr>
            <th align="right">${id}:</th>
            <td align="left">
                <c:if test="${!newrace}">
                    ${race.getRaceId()}
                </c:if>
            </td>

            <th align="right">${name}:</th>
            <td align="left">
                <c:if test="${!newrace}">
                    ${race.getRaceName()}
                </c:if>
                <input type="text" name="racename"/>
            </td>

            <th align="right">${date}:</th>
            <td align="left">
                <c:if test="${!newrace}">
                    ${race.getRaceFormatDate()}
                </c:if>
                <input type="datetime-local" name="racedate"/>
            </td>

            <th align="right">${winner}:</th>
            <td align="left">
                <c:if test="${!newrace}">
                    ${race.getHorseWinnerName()}
                </c:if>
                <!--<input type="text" name="horsewinner"/>-->
                <select name="winhorse">
                    <option selected disabled>${choosehorse}</option>
                    <c:forEach var="winhorse" items="${winninghorselist}">
                        <option value="${winhorse.getHorseId()}">${winhorse.getHorseName()}</option>
                    </c:forEach>
                </select>
            </td>
        </tr>
    </table>
    <input type="hidden" name="newrace" value="${newrace}"/>
    <input type="hidden" name="raceid" value="${race.getRaceId()}"/>
    <input type="submit" name="save" value="${save}"/>
</form>

<c:if test="${!newrace}">
    <form action="/races/admin/edit" method="post">
        <input type="hidden" name="raceid" value="${race.getRaceId()}"/>
        <input type="hidden" name="delete" value="${true}">
        <input type="submit" value="${delete}" />
    </form>

    <c:forEach var="raceLine" items="${race.getRaceLineList()}">
        <table style="border: 1px solid #000;" cellpadding="5" cellspacing="5">
            <tr>
                <th>${horse}</th>
                <th>${odd}</th>
                <th>
                    <form action="/races/admin/edit" method="post">
                        <input type="hidden" name="newraceline" value="${false}"/>
                        <input type="hidden" name="delete" value="${true}"/>
                        <input type="hidden" name="raceline" value="${true}"/>
                        <input type="hidden" name="raceid" value="${raceLine.getRaceId()}"/>
                        <input type="hidden" name="linenum" value="${raceLine.getLineNum()}"/>
                        <input type="submit" value="${delete}"/>
                    </form>
                </th>
            </tr>
            <tr>
                <form action="/races/admin/edit" method="post">
                    <td>
                        ${raceLine.getHorseName()} <br/>
                        <!--<input type="text" name="horsename"/>-->
                        <select name="horsename">
                            <c:forEach var="addhorse" items="${horselist}">
                                <c:choose>
                                    <c:when test="${addhorse.getHorseId() == raceLine.getHorseId()}">
                                        <option selected value="${addhorse.getHorseId()}">${addhorse.getHorseName()}</option>
                                    </c:when>
                                    <c:otherwise>
                                        <option value="${addhorse.getHorseId()}">${addhorse.getHorseName()}</option>
                                    </c:otherwise>
                                </c:choose>

                            </c:forEach>
                        </select>
                    </td>
                    <td>
                        ${raceLine.getOdd()} <br/>
                        <input type="text" name="odd" title="0.00" pattern="\d+(\.\d{2})?"/>
                    </td>
                    <td>
                        <input type="hidden" name="newraceline" value="${false}"/>
                        <input type="hidden" name="save" value="${true}"/>
                        <input type="hidden" name="raceline" value="${true}"/>
                        <input type="hidden" name="raceid" value="${raceLine.getRaceId()}"/>
                        <input type="hidden" name="linenum" value="${raceLine.getLineNum()}"/>
                        <input type="submit" value="${save}"/>
                    </td>
                </form>
            </tr>
        </table>
    </c:forEach>
</c:if>

<c:if test="${!newrace}">
    <br/>
    <form action="/races/admin/edit" method="post">
        <table  style="border: 1px solid #000;" cellpadding="5" cellspacing="5">
            <tr>
                <th>${horse}</th>
                <th>${odd}</th>
            </tr>
            <tr>
                <!--<td><input type="text" name="horsename" required/></td>-->
                <td>
                    <select name="horsename" required>
                        <option selected disabled>${choosehorse}</option>
                        <c:forEach var="addhorse" items="${horselist}">
                            <option value="${addhorse.getHorseId()}">${addhorse.getHorseName()}</option>
                        </c:forEach>
                    </select>
                </td>
                <td><input type="text" name="odd" title="0.00" pattern="\d+(\.\d{2})?" required/></td>
            </tr>
        </table>
        <input type="hidden" name="raceid" value="${race.getRaceId()}"/>
        <input type="hidden" name="raceline" value="${true}"/>
        <input type="hidden" name="addline" value="${true}">
        <input type="submit" value="${addline}" />
    </form>
</c:if>

<br/>
<li><a href="/races">${races}</a></li>
<li><a href="/index.jsp">${main}</a></li>
</body>
</html>
