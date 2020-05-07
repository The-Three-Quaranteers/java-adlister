<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<nav class="navbar navbar-default">
    <div class="container-fluid">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
            <a class="navbar-brand" href="/ads">Adlister</a>
        </div>

        <ul class="nav navbar-nav navbar-right align-item-center">
            <li class="nav-item">
                <form class="navbar-form" action="/ads/search" method="get">
                    <label for="search">Search:</label>
                    <input id="search" name="search" class="form-control" type="text" placeholder="Ad Title">
<%--                    <button type="submit" class="btn btn-sm">Search</button>--%>
                </form>
            </li>
            <c:choose>
                <c:when test="${user == null}">
                    <li class="nav-item"><a href="/login">Login</a></li>
                    <li class="nav-item"><a href="/register">Register</a></li>
                </c:when>
                <c:otherwise>
                    <li class="nav-item"><a href="/ads">View all ads</a></li>
                    <li class="nav-item"><a href="/ads/create">Create new ad</a></li>
                    <li class="nav-item"><a href="/profile">My ads</a></li>
                    <li class="nav-item"><a href="/logout">Logout</a></li>
                </c:otherwise>
            </c:choose>
        </ul>
<%--    </div><!-- /.navbar-collapse -->--%>
    </div><!-- /.container-fluid -->
</nav>

