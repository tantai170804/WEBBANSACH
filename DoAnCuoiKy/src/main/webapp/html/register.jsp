<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Đăng ký</title>
    <link href="${pageContext.request.contextPath}/css/login.css" rel="stylesheet" type="text/css" >
</head>
<jsp:include page="header.jsp" />
<body>

<h2>Đăng ký</h2>

<form method="post" action="${pageContext.request.contextPath}/register">

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
               required />
    </div>

    <div>
        <input type="password"
               name="confirmPassword"
               placeholder="Xác nhận mật khẩu"
               required />
    </div>

    <div>
        <button type="submit">Register</button>
    </div>

    <!-- Hiển thị lỗi -->
    <c:if test="${not empty error}">
        <p class="error">
            <c:out value="${error}" />
        </p>
    </c:if>
	<p>
    Đã có tài khoản?
    	<a href="${pageContext.request.contextPath}/login">Đăng nhập</a>
	</p>
</form>



</body>
<jsp:include page="footer.jsp" />
</html>
