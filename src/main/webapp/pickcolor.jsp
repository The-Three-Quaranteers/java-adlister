<%--
  Created by IntelliJ IDEA.
  User: brycepayne
  Date: 4/21/20
  Time: 1:49 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String bgcolor;
    if (request.getParameterMap().containsKey("color")) {
        bgcolor = request.getParameter("color");
    }
    else {
        bgcolor = "white";
    }

%>
<html>

<head>
    <title>Pick Color</title>
    <style type="text/css">
        body {background: <% bgcolor %>}
    </style>
</head>
<body>

<form action="/pickcolor.jsp" method="post">

    <label for="color">Please enter a color:</label>
    <input type="text" id="color">
    <button type="submit">Submit</button>
    <% response.sendRedirect("/pickcolor.jsp?color=" + bgcolor); %>
</form>

</body>
</html>
