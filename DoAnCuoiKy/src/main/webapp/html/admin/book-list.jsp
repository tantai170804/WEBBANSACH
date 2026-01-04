<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Admin - Book List</title>
<style>
body {
	font-family: Arial, sans-serif;
	background: #f5f7fb;
	margin: 0;
	padding: 24px;
	color: #111827;
}

.container {
	max-width: 1100px;
	margin: 0 auto;
}

.header {
	display: flex;
	justify-content: space-between;
	align-items: flex-end;
	gap: 12px;
	margin-bottom: 14px;
}

h2 {
	margin: 0;
	font-size: 26px;
	font-weight: 800;
}

.sub {
	margin: 6px 0 0;
	color: #6b7280;
	font-size: 14px;
}

.btn {
	display: inline-flex;
	align-items: center;
	justify-content: center;
	gap: 8px;
	border: 0;
	border-radius: 10px;
	padding: 10px 14px;
	cursor: pointer;
	font-weight: 700;
	font-size: 14px;
	text-decoration: none;
	white-space: nowrap;
}

.btn-add {
	background: #22c55e;
	color: #fff;
}

.btn-add:hover {
	background: #16a34a;
}

.card {
	background: #fff;
	border: 1px solid #e5e7eb;
	border-radius: 12px;
	box-shadow: 0 6px 18px rgba(0, 0, 0, 0.06);
	overflow: hidden;
}

table {
	width: 100%;
	border-collapse: separate;
	border-spacing: 0;
}

thead th {
	background: #f9fafb;
	text-align: left;
	font-size: 13px;
	letter-spacing: .3px;
	text-transform: uppercase;
	color: #374151;
	padding: 12px 12px;
	border-bottom: 1px solid #e5e7eb;
}

tbody td {
	padding: 12px 12px;
	border-bottom: 1px solid #eef2f7;
	vertical-align: middle;
	font-size: 14px;
}

tbody tr:hover {
	background: #fafcff;
}

.col-id {
	width: 70px;
}

.col-code {
	width: 120px;
}

.col-price {
	width: 120px;
	text-align: right;
}

.col-qty {
	width: 80px;
	text-align: right;
}

.col-actions {
	width: 170px;
}

.badge {
	display: inline-block;
	padding: 6px 10px;
	border-radius: 999px;
	font-weight: 800;
	font-size: 12px;
	border: 1px solid transparent;
}

.badge-show {
	background: #dcfce7;
	color: #166534;
	border-color: #bbf7d0;
}

.badge-hide {
	background: #fee2e2;
	color: #991b1b;
	border-color: #fecaca;
}

.actions {
	display: flex;
	gap: 8px;
	align-items: center;
}

.btn-small {
	padding: 8px 10px;
	border-radius: 10px;
	font-size: 13px;
	font-weight: 800;
}

.btn-edit {
	background: #2563eb;
	color: #fff;
}

.btn-edit:hover {
	background: #1d4ed8;
}

.btn-delete {
	background: #ef4444;
	color: #fff;
}

.btn-delete:hover {
	background: #dc2626;
}

form {
	margin: 0;
}

/* Responsive: cuộn ngang nếu màn nhỏ */
.table-wrap {
	overflow: auto;
}
</style>
</head>
<body>

	<h2>Danh sách sách (Admin)</h2>

	<p>
		<a href="${pageContext.request.contextPath}/admin/books/create">+
			Thêm sách</a>
	</p>

	<table border="1" cellpadding="6">
		<tr>
			<th>ID</th>
			<th>Mã</th>
			<th>Tên</th>
			<th>Thể loại</th>
			<th>Giá</th>
			<th>SL</th>
			<th>Trạng thái</th>
			<th>Hành động</th>
		</tr>

		<c:forEach items="${books}" var="b">
			<tr>
				<td>${b.bookId}</td>
				<td>${b.bookCode}</td>
				<td><c:out value="${b.title}" /></td>
				<td><c:out value="${b.categoryName}" /></td>
				<td><fmt:formatNumber value="${b.price}" type="number"
						groupingUsed="true" /></td>
				<td>${b.quantityInStock}</td>
				<td><c:choose>
						<c:when test="${b.canShow}">Hiển thị</c:when>
						<c:otherwise>Ẩn</c:otherwise>
					</c:choose></td>
				<td><a
					href="${pageContext.request.contextPath}/admin/books/edit?id=${b.bookId}">Sửa</a>
					<form method="post"
						action="${pageContext.request.contextPath}/admin/books/delete"
						style="display: inline">
						<input type="hidden" name="id" value="${b.bookId}" />
						<button type="submit" onclick="return confirm('Xóa sách này?')">
							Xóa</button>
					</form></td>
			</tr>
		</c:forEach>
	</table>
	<div
		style="margin-top: 16px; display: flex; gap: 8px; align-items: center; flex-wrap: wrap;">
		<c:set var="base"
			value="${pageContext.request.contextPath}/admin/books?size=${size}&page=" />

		<c:if test="${page > 1}">
			<a href="${base}${page-1}">« Trước</a>
		</c:if>

		<c:forEach begin="1" end="${totalPages}" var="p">
			<c:choose>
				<c:when test="${p == page}">
					<strong
						style="padding: 6px 10px; border: 1px solid #333; border-radius: 8px;">${p}</strong>
				</c:when>
				<c:otherwise>
					<a
						style="padding: 6px 10px; border: 1px solid #ddd; border-radius: 8px; text-decoration: none;"
						href="${base}${p}">${p}</a>
				</c:otherwise>
			</c:choose>
		</c:forEach>

		<c:if test="${page < totalPages}">
			<a href="${base}${page+1}">Sau »</a>
		</c:if>

		<span style="color: #666; margin-left: 8px;"> Tổng:
			${totalItems} sách </span>
	</div>

</body>
</html>
