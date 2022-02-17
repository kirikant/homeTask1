<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="project.services.Storage" %>
<%@ page import="project.dto.Message" %>
<%@ page import="java.time.LocalDateTime" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Map" %><%--
  Created by IntelliJ IDEA.
  User: user
  Date: 10.02.2022
  Time: 13:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>message</title>
</head>
<body>
<form action="<c:url value="/message"/>" method="post">
    <label>send to (login)
        <input type="text" name="receiver">
    </label>
    <label>message
        <input type="text" name="message">
    </label>
    <button name="button" type="submit">send</button>
    </form>
<form action="<c:url value="/messenger/profile.jsp"/>" ><button type="submit">back</button></form>
</body>
</html>
