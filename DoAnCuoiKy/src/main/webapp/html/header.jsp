<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
          rel="stylesheet">
<link href="${pageContext.request.contextPath}/css/styles.css" rel="stylesheet" type="text/css" > <!-- Liên kết file CSS -->
<header>
    <h1>Chào Mừng Đến Với Cửa Hàng Bán Sách</h1>
    <nav>
        <a href="${pageContext.request.contextPath}/html/home">Trang Chủ</a>
        <a href="${pageContext.request.contextPath}/randombook">Sách ngẫu nhiên</a>
        <a href="${pageContext.request.contextPath}/cart">Giỏ hàng</a>
        <c:if test="${empty sessionScope.currentUser}">
        <a href="${pageContext.request.contextPath}/login">Đăng nhập</a>
        </c:if>
        <c:if test="${not empty sessionScope.currentUser}">
         
        	<a href="${pageContext.request.contextPath}/logout">Đăng xuất</a>
        </c:if>
     
    </nav>
</header>