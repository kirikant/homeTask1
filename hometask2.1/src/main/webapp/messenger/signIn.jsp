<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>signIn</title>
</head>
<body>
<form action="<c:url value="/signIn"/>" method="post">
    <label>login
        <input type="text" name="login">
    </label>
    <label>password
        <input type="text" name="password">
    </label>
    <button type="submit" name="submit">submit</button>
</form>
<form action="<c:url value="/messenger/startPage.jsp"/>">
    <button type="submit">back</button>
</form>
<c:if test="${requestScope.errorIn!=null}">
    <c:out value="${requestScope.errorIn}"/>
    <c:set var="errorIn" scope="request" value="${null}"/>
</c:if>
</body>
</html>
