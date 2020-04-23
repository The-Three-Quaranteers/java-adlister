<%--
  Created by IntelliJ IDEA.
  User: brycepayne
  Date: 4/22/20
  Time: 3:55 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="../partials/head.jsp">
        <jsp:param name="title" value="Adlister Ads"/>
    </jsp:include>
</head>
<body>
    <jsp:include page="../partials/navbar.jsp"/>

    <h1>Check out all these cool ads!!!:</h1>

<c:forEach var="ads" items="${ads}">
    <div class="ad">
        <h2>${ads.title}</h2>
        <p>User ID: ${ads.userId}</p>
        <p>Post ID: ${ads.id}</p>
        <h3>Description:</h3>
        <p>${ads.description}</p>
    </div>
</c:forEach>
</body>
</html>
