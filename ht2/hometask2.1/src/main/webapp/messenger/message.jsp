<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="project.storages.MapStorage" %>
<%@ page import="project.entity.MessageEntity" %>
<%@ page import="java.time.LocalDateTime" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Map" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>messageEntity</title>
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
<c:if test="${requestScope.errorUp!=null}">
    <c:out value="${requestScope.errorUp}"/>
    <c:set var="errorUp" scope="request" value="${null}"/>
</c:if>
<form action="<c:url value="/messenger/profile.jsp"/>" ><button type="submit">back</button></form>
</body>
</html>
