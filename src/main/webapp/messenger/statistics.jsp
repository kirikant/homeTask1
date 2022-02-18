<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>statistics</title>
</head>
<body>
<h3>Statistics:</h3>
<table  border="2px">
    <tr>
        <th>number of users</th>
        <th>number of messages</th>
        <th>number of sessions</th>
    </tr>
<tr>
    <td><c:out value="${requestScope.usersAmount}"/></td>
    <td><c:out value="${requestScope.messagesAmount}"/></td>
    <td><c:out value="${requestScope.sessionsAmount}"/></td>
</tr>
</table>
<form action="<c:url value="/messenger/profile.jsp"/>" ><button type="submit">back</button></form>
</body>
</html>
