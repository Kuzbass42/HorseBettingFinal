<%@ page pageEncoding="utf-8" contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:useBean id="userlist" type="java.util.ArrayList" scope="request"/>

<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="locale" var="loc"/>
<fmt:message bundle="${loc}" key="local.users.title" var="title"/>
<fmt:message bundle="${loc}" key="local.users.userid" var="userid"/>
<fmt:message bundle="${loc}" key="local.users.username" var="username"/>
<fmt:message bundle="${loc}" key="local.users.userlogin" var="userlogin"/>
<fmt:message bundle="${loc}" key="local.users.userpassword" var="userpassword"/>
<fmt:message bundle="${loc}" key="local.users.userbalance" var="userbalance"/>
<fmt:message bundle="${loc}" key="local.users.userrole" var="userrole"/>
<fmt:message bundle="${loc}" key="local.main" var="main"/>
<fmt:message bundle="${loc}" key="local.races" var="races"/>
<fmt:message bundle="${loc}" key="local.horses" var="horses"/>
<fmt:message bundle="${loc}" key="local.odds" var="odds"/>
<html>
<head>
    <title>${title}</title>
</head>
<body>

    <table style="border: 1px solid #000;" cellpadding="5" cellspacing="5">
        <tr>
            <th>${userid}</th>
            <th>${username}</th>
            <th>${userlogin}</th>
            <th>${userpassword}</th>
            <th>${userbalance}</th>
            <th>${userrole}</th>
        </tr>
        <c:forEach var="user" items="${userlist}">
            <tr>
                <td>
                        ${user.getUserId()}
                </td>
                <td>
                        ${user.getUserName()}
                </td>
                <td>
                        ${user.getLogin()}
                </td>
                <td>
                        ${user.getPassword()}
                </td>
                <td>
                        ${user.getBalance()}
                </td>
                <td>
                        ${user.getUserRole()}
                </td>
            </tr>
        </c:forEach>
    </table>


    <br/>
    <li><a href="/index.jsp">${main}</a></li>
</body>
</html>
