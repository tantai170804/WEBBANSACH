<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="activePage" value="users" scope="request" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Admin - Quản lý user</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/admin.css">
</head>

<body>
	<div class="admin-layout">

		<jsp:include page="_sidebar.jsp" />

		<div class="content">

			<div class="page-header">
				<div>
					<h1>Danh sách user</h1>
					<p class="sub">Quản lý tài khoản người dùng</p>
				</div>
			</div>

			<c:if test="${param.msg == 'deleted'}">
				<div class="alert alert-success">Xóa user thành công</div>
			</c:if>

			<c:if test="${param.msg == 'fail'}">
				<div class="alert alert-danger">Không thể xóa user</div>
			</c:if>

			<div class="card">
				<div class="card-body">
					<table class="table">
						<thead>
							<tr>
								<th>ID</th>
								<th>Username</th>
								<th>Email</th>
								<th>Phone</th>
								<th>Role</th>
								<th>Ngày tạo</th>
								<th class="text-right">Hành động</th>
							</tr>
						</thead>

						<tbody>
							<c:forEach items="${users}" var="u">
								<tr>
									<td>${u.userId}</td>
									<td class="title">${u.userName}</td>
									<td>${u.email}</td>
									<td>${u.phone}</td>
									<td><span class="badge">${u.role}</span></td>
									<td>${u.createAt}</td>
									<td class="text-right"><a class="btn btn-sm btn-outline"
										href="${pageContext.request.contextPath}/admin/users/edit?id=${u.userId}">
											Sửa </a>

										<form method="post"
											action="${pageContext.request.contextPath}/admin/users/delete"
											style="display: inline">
											<input type="hidden" name="id" value="${u.userId}">
											<button class="btn btn-sm btn-danger"
												onclick="return confirm('Xóa user này?')">Xóa</button>
										</form></td>
								</tr>
							</c:forEach>

							<c:if test="${empty users}">
								<tr>
									<td colspan="7" style="padding: 14px; color: #6b7280">
										Chưa có user</td>
								</tr>
							</c:if>
						</tbody>
					</table>
				</div>
			</div>

		</div>
	</div>
</body>
</html>
