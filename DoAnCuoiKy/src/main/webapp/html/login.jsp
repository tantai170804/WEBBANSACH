<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Đăng nhập</title>
    <link href="${pageContext.request.contextPath}/css/login.css" rel="stylesheet" type="text/css" >
</head>
<body>
<jsp:include page="header.jsp" />
<h2>Đăng nhập</h2>
<form method="post" action="${pageContext.request.contextPath}/login">
    <div>
        <input type="text"
               name="username"
               placeholder="Tên đăng nhập"
               value="${username}"
               required />
    </div>

    <div>
        <input type="password"
               name="password"
               placeholder="Mật khẩu"
               required
               autocomplete="current-password" />
    </div>

    <div>
        <button type="submit">Đăng nhập</button>
    </div>
 	
    <c:if test="${not empty error}">
        <p class="error">
            <c:out value="${error}" />
        </p>
    </c:if>
	<p>Chưa có tài khoản? <a href="${pageContext.request.contextPath}/register">Đăng ký ngay!</a></p>
</form>
<jsp:include page="footer.jsp" />
</body>
</html>