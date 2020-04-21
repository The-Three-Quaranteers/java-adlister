<%--
  Created by IntelliJ IDEA.
  User: brycepayne
  Date: 4/21/20
  Time: 11:09 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    if(request.getMethod().equalsIgnoreCase("post")) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        if(username.equals("Bryce") && password.equals("codeup")) {
            response.sendRedirect("/profile.jsp");
        }
    }
%>
<html>
<head>
    <title>Login</title>
    <%@include file="partials/css.jsp"%>

</head>
<body>
<%@include file="partials/navbar.jsp"%>

<form action="login.jsp" method="post">
    <label for="username">User Name:</label>
    <input type="text" id="username" name="username">
    <label for="password">Password:</label>
    <input type="password" id="password" name="password">
    <input class="btn btn-default" type="submit" value="Login">
</form>

<%@include file="partials/javascript.jsp"%>
</body>
</html>
