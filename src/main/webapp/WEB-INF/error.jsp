<%@ page pageEncoding="utf-8" contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="locale" var="loc"/>
<fmt:message bundle="${loc}" key="local.username" var="username"/>
<fmt:message bundle="${loc}" key="local.password" var="password"/>
<fmt:message bundle="${loc}" key="local.login" var="login"/>
<fmt:message bundle="${loc}" key="local.main" var="main"/>
<fmt:message bundle="${loc}" key="local.error" var="error"/>
<fmt:message bundle="${loc}" key="local.regpage" var="regpage"/>
<html>
<head>
    <title>${error}</title>
</head>
<body>
<h1>${error}</h1>

<%@ include file="/login.jsp"%>

<li><a href="/reg.jsp"> ${regpage}</a></li>
</body>
</html>
