<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="project.storages.MapStorage" %>
<%@ page import="project.entity.MessageEntity" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>chats</title>
</head>
<body>
<h3><%= "Your massages" %></h3>

<c:forEach var="messageEntity" items="${requestScope.messagesList}">
    ${messageEntity}<br>
</c:forEach>
<form action="<c:url value="/messenger/profile.jsp"/>">
    <button type="submit">back</button>
</form>
</body>
</html>
