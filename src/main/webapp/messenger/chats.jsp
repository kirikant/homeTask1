<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="project.services.Storage" %>
<%@ page import="project.dto.Message" %>
<%@ page import="java.util.ArrayList" %>
<%--
 Created by IntelliJ IDEA.
 User: user
 Date: 11.02.2022
 Time: 10:02
 To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>chats</title>
</head>
<body>
<h3><%= "Your massages" %></h3>
<%--<%--%>
<%--    HttpSession session1 = request.getSession();--%>
<%--    String login = (String) session1.getAttribute("login");--%>
<%--    Storage storage = Storage.getStorage();--%>
<%--    ArrayList<Message> messages = storage.getMessages().get(login);--%>
<%--    if (messages != null) {--%>
<%--        for (Message message : messages) {--%>
<%--            response.getWriter().write(String.valueOf(message));--%>
<%--        }--%>
<%--    }--%>
<%--%>--%>
<c:forEach var="message" items="${requestScope.messagesList}">
    ${message}<br>
</c:forEach>
<form action="<c:url value="/messenger/profile.jsp"/>">
    <button type="submit">back</button>
</form>
</body>
</html>
