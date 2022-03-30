<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
