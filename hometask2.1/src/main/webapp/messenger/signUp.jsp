<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
