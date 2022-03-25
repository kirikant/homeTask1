<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.io.PrintWriter" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>startPage</title>
</head>
<body>

<%=  "Welcome!" %>
<form action="<c:url value="/messenger/signIn.jsp"/>" ><button type="submit">sign in</button></form>
<form action="<c:url value="/messenger/signUp.jsp"/>" ><button type="submit">sign up</button></form>
</body>
</html>
