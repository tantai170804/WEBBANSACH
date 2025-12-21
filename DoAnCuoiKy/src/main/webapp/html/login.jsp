
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<form method="post" action="${pageContext.request.contextPath}/login">

    <input type="text" name="username" placeholder="Username" value="${username}" required />

    <input type="password" name="password" placeholder="Password" required autocomplete="current-password" />

    <button type="submit">Login</button>

    <c:if test="${not empty error}">
        <p style="color:red"><c:out value="${error}" /></p>
    </c:if>

</form>

