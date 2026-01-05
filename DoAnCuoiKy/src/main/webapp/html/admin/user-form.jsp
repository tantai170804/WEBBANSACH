<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="activePage" value="users" scope="request" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Admin - Sửa user</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/admin.css">
</head>

<body>
	<div class="admin-layout">

		<jsp:include page="_sidebar.jsp" />

		<div class="content">

			<div class="page-header">
				<div>
					<h1>Sửa user</h1>
					<p class="sub">Chỉnh sửa thông tin tài khoản</p>
				</div>
			</div>

			<c:if test="${not empty error}">
				<div class="alert alert-danger">${error}</div>
			</c:if>

			<div class="card">
				<div class="card-body">

					<form method="post">

						<input type="hidden" name="userId" value="${user.userId}" />

						<div style="margin-bottom: 12px">
							<label>Username</label> <input type="text"
								value="${user.userName}" disabled style="width: 100%" />
						</div>

						<div style="margin-bottom: 12px">
							<label>Email</label> <input type="text" name="email"
								value="${user.email}" style="width: 100%" />
						</div>

						<div style="margin-bottom: 12px">
							<label>Phone</label> <input type="text" name="phone"
								value="${user.phone}" style="width: 100%" />
						</div>

						<div style="margin-bottom: 12px">
							<label>Address</label> <input type="text" name="address"
								value="${user.address}" style="width: 100%" />
						</div>

						<div style="margin-bottom: 12px">
							<label>Role</label> <select name="role" style="width: 100%">
								<option value="user" ${user.role == 'user' ? 'selected' : ''}>
									User</option>
								<option value="admin" ${user.role == 'admin' ? 'selected' : ''}>
									Admin</option>
							</select>
						</div>

						<div style="display: flex; gap: 10px">
							<button class="btn btn-primary" type="submit">Lưu thay
								đổi</button>

							<a class="btn btn-outline"
								href="${pageContext.request.contextPath}/admin/users"> Hủy </a>
						</div>

					</form>

				</div>
			</div>

		</div>
	</div>
</body>
</html>
