<%@ page import="project.services.Storage" %>
<%@ page import="project.dto.User" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 13.02.2022
  Time: 11:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>profile</title>
</head>
<body>
<h3>Profile:</h3>
<%--<% String login = (String)request.getSession().getAttribute("login");--%>
<%--    User user = Storage.getStorage().getUsersLogins().get(login);%>--%>
<%--<%=user.getLogin()%>--%>
<%--<%=user.getName()%>--%>
<%--<%=user.getBirthDay()%>--%>
<br><c:out value="${sessionScope.profileInfo.login}"/>
<br><c:out value="${sessionScope.profileInfo.name}"/>
<br><c:out value="${sessionScope.profileInfo.birthDay}"/>
<form method="get" action="<c:url value="/chats"/>" ><button type="submit">chats</button></form>
<form action="<c:url value="/messenger/message.jsp"/>" ><button type="submit">send message</button></form>
<form action="<c:url value="/messenger/startPage.jsp"/>" ><button type="submit">sign out</button></form>
</body>
</html>
