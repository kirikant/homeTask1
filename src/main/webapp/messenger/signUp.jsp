<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 09.02.2022
  Time: 21:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>signUp</title>
</head>
<body>
<form action="<c:url value="/signUp"/>" method="post">
    <label>login
        <input type="text" name="login">
    </label>
    <label>password
        <input type="text" name="password">
    </label>
    <label>name
        <input type="text" name="name">
    </label>
    <label>birthday
        <input type="date" name="birthDay">
    </label>
    <button type="submit" name="submit" >submit</button>
        </form>
<c:if test="${requestScope.errorUp!=null}">
    <c:out value="${requestScope.errorUp}"/>
    <c:set var="errorUp" scope="request" value="${null}"/>
</c:if>
<form action="<c:url value="/messenger/startPage.jsp"/>" ><button type="submit">back</button></form>
</body>
</html>
