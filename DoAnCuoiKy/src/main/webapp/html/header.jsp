<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<header>
    <h1>Chào Mừng Đến Với Cửa Hàng Bán Sách</h1>
    <nav>
        <a href="${pageContext.request.contextPath}/html/home">Trang Chủ</a>
        <a href="${pageContext.request.contextPath}/html/randombook">Sách ngẫu nhiên</a>
        <c:if test="${empty sessionScope.currentUser}">
        <a href="${pageContext.request.contextPath}/login">Đăng nhập</a>
        </c:if>
        <c:if test="${not empty sessionScope.currentUser}">
        <a href="${pageContext.request.contextPath}/logout">Đăng xuất</a>
        </c:if>
    </nav>
</header>