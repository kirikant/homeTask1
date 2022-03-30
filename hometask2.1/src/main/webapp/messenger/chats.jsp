<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>chats</title>
</head>
<body>
<h3><%= "Your massages" %></h3>
<c:if test="${sessionScope.size1>0}">
<c:forEach var="messageEntity" items="${requestScope.messagesList}">
    ${messageEntity}<br>
</c:forEach>
<form action="<c:url value="/messenger/profile.jsp"/>" >
    <button type="submit">back</button>
</form>

<form action="<c:url value="/chats"/>" method="post">
<%--    <input type="hidden" name="up" value="up"/>--%>
    <button type="submit" id="up"  name="up" value="up" >next page</button>
</form>
</c:if>
<c:if test="${sessionScope.size1<1}">
    <%="there is no messages left"%>
</c:if>

<c:if test="${sessionScope.page>1}">
<form action="<c:url value="/chats"/>" method="post">
<%--    <input type="hidden" name="down" value="down"/>--%>
    <button type="submit" id="down" name="down" value="down" >previous page</button>
</form>
</c:if>


</body>
</html>
