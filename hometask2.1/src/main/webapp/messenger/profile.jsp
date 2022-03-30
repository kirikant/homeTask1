<%@ page import="project.entity.UserEntity" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>profile</title>
</head>
<body>
<h3>Profile:</h3>
<br><c:out value="${sessionScope.profileInfo.login}"/>
<br><c:out value="${sessionScope.profileInfo.name}"/>
<br><c:out value="${sessionScope.profileInfo.birthDay}"/>
<form method="get" action="<c:url value="/statistics"/>" ><button type="submit">statistics</button></form>
<form method="get" action="<c:url value="/chats"/>" ><button type="submit">chats</button></form>
<form action="<c:url value="/messenger/message.jsp"/>" ><button type="submit">send message</button></form>
<form action="<c:url value="/messenger/startPage.jsp"/>" ><button type="submit">sign out</button></form>
<form method="get" action="<c:url value="/audit"/>" ><button type="submit">my actions</button></form>
</body>
</html>
