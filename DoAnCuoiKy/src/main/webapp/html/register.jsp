<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Register</title>
</head>
<body>

<h2>Register</h2>

<form method="post" action="${pageContext.request.contextPath}/register">

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
               required />
    </div>

    <div>
        <input type="password"
               name="confirmPassword"
               placeholder="Confirm Password"
               required />
    </div>

    <div>
        <input type="text"
               name="fullName"
               placeholder="Full name"
               value="${fullName}" />
    </div>

    <div>
        <button type="submit">Register</button>
    </div>

    <!-- Hiển thị lỗi -->
    <c:if test="${not empty error}">
        <p style="color:red">
            <c:out value="${error}" />
        </p>
    </c:if>

</form>

<p>
    Đã có tài khoản?
    <a href="${pageContext.request.contextPath}/login">Login</a>
</p>

</body>
</html>
