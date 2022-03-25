<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 25.03.2022
  Time: 13:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>audit</title>
</head>
<body>
<h3><%= "Your actions" %></h3>

<c:forEach var="audit" items="${sessionScope.audits}">
    ${audit}<br>
</c:forEach>
<form action="<c:url value="/messenger/profile.jsp"/>">
    <button type="submit">back</button>
</form>
</body>
</html>
