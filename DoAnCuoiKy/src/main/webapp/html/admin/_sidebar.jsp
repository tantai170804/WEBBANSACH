<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="sidebar">
	<div class="brand">ADMIN</div>

	<a class="nav-item ${activePage == 'dashboard' ? 'active' : ''}"
		href="${pageContext.request.contextPath}/admin/dashboard"> 📊
		Dashboard </a> <a
		class="nav-item ${activePage == 'books' ? 'active' : ''}"
		href="${pageContext.request.contextPath}/admin/books"> 📚 Quản lý
		sách </a> <a
		class="nav-item ${activePage == 'categories' ? 'active' : ''}"
		href="${pageContext.request.contextPath}/admin/categories"> 🏷
		Quản lý thể loại </a> <a
		class="nav-item ${activePage=='users'?'active':''}"
		href="${pageContext.request.contextPath}/admin/users"> 👤 Quản lý
		user </a>


	<div class="divider"></div>

	<a class="nav-item" href="${pageContext.request.contextPath}/html/home">
		← Về trang user </a> <a class="nav-item danger"
		href="${pageContext.request.contextPath}/logout"> ⎋ Đăng xuất </a>
</div>
