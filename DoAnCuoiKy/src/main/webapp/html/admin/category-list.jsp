<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Quản lý thể loại</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/admin.css">
</head>
<body>
	<div class="admin-layout">
		<c:set var="activePage" value="categories" scope="request" />
		<jsp:include page="_sidebar.jsp" />

		<div class="content">
			<div class="page-header">
				<div>
					<h1>Danh sách thể loại</h1>
					<p class="sub">Quản lý các thể loại sách trong hệ thống</p>
				</div>

				<a class="btn btn-primary"
					href="${pageContext.request.contextPath}/admin/categories/create">
					+ Thêm thể loại </a>
			</div>

			<c:if test="${param.msg == 'used'}">
				<div class="alert alert-warn">Không thể xoá: thể loại đang
					được sách sử dụng.</div>
			</c:if>

			<div class="card">
				<div class="card-body">
					<table class="table">
						<thead>
							<tr>
								<th style="width: 90px;">ID</th>
								<th>Tên</th>
								<th>Mô tả</th>
								<th class="text-right" style="width: 170px;">Hành động</th>
							</tr>
						</thead>

						<tbody>
							<c:forEach items="${categories}" var="c">
								<tr>
									<td>${c.categoryId}</td>
									<td class="title">${c.name}</td>
									<td>${c.description}</td>
									<td class="text-right"><a class="btn btn-sm btn-outline"
										href="${pageContext.request.contextPath}/admin/categories/edit?id=${c.categoryId}">
											Sửa </a>

										<form method="post"
											action="${pageContext.request.contextPath}/admin/categories/delete"
											style="display: inline; margin: 0;">
											<input type="hidden" name="id" value="${c.categoryId}" />
											<button class="btn btn-sm btn-danger" type="submit"
												onclick="return confirm('Xóa thể loại này?')">Xóa</button>
										</form></td>
								</tr>
							</c:forEach>

							<c:if test="${empty categories}">
								<tr>
									<td colspan="4" style="padding: 14px; color: #6b7280;">
										Chưa có thể loại.</td>
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
