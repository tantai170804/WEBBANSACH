<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login</title>
</head>
<body>

<h2>Login</h2>

<form method="post" action="${pageContext.request.contextPath}/login">

    <div>
        <input type="text"
               name="username"
               placeholder="Username"
               value="${username}"
               required />
    </div>

    <div>
        <input type="password"
               name="password"
               placeholder="Password"
               required
               autocomplete="current-password" />
    </div>

    <div>
        <button type="submit">Login</button>
    </div>

    <c:if test="${not empty error}">
        <p style="color:red">
            <c:out value="${error}" />
        </p>
    </c:if>

</form>

</body>
</html>
