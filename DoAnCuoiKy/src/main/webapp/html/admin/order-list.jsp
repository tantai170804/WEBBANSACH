<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<c:set var="activePage" value="orders" scope="request" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Admin - Quản lý đơn hàng</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/admin.css">
<style>
/* =========================
   ADMIN BASE (ĐÃ SỬA LỖI CÚ PHÁP)
   ========================= */
:root {
	--bg: #f5f7fb;
	--text: #111827;
	--muted: #6b7280;
	--sidebar: #111827;
	--primary: #2563eb;
	--danger: #ef4444;
	--border: #e5e7eb;
	--shadow: 0 4px 6px -1px rgba(0, 0, 0, 0.1), 0 2px 4px -1px
		rgba(0, 0, 0, 0.06);
}

* {
	box-sizing: border-box;
}

body {
	margin: 0;
	font-family: -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto,
		Arial, sans-serif;
	background: var(--bg);
	color: var(--text);
	-webkit-font-smoothing: antialiased; /* Giúp chữ sắc nét hơn */
}

/* =========================
   LAYOUT
   ========================= */
.admin-layout {
	display: flex;
	min-height: 100vh;
}

.content {
	flex: 1;
	padding: 24px;
	overflow-y: auto;
}

/* =========================
   SIDEBAR
   ========================= */
.sidebar {
	width: 240px;
	background: var(--sidebar);
	color: #fff;
	padding: 20px 14px;
	position: sticky;
	top: 0;
	height: 100vh;
	flex-shrink: 0;
}

.brand {
	font-weight: 800;
	font-size: 18px;
	padding: 12px;
	margin-bottom: 20px;
	text-align: center;
	background: rgba(255, 255, 255, .1);
	border-radius: 8px;
}

.nav-item {
	display: block;
	padding: 12px;
	border-radius: 8px;
	color: #9ca3af;
	text-decoration: none;
	font-weight: 600;
	margin-bottom: 4px;
	transition: 0.2s;
}

.nav-item:hover {
	background: rgba(255, 255, 255, .1);
	color: #fff;
}

.nav-item.active {
	background: var(--primary);
	color: #fff;
}

/* =========================
   HEADER & BUTTONS
   ========================= */
.page-header {
	display: flex;
	align-items: flex-start;
	justify-content: space-between;
	margin-bottom: 24px;
}

.page-header h1 {
	margin: 0;
	font-size: 24px;
	font-weight: 800;
}

.sub {
	margin: 4px 0 0;
	color: var(--muted);
	font-size: 14px;
}

.btn {
	display: inline-flex;
	align-items: center;
	gap: 8px;
	padding: 10px 16px;
	border-radius: 8px;
	text-decoration: none;
	font-weight: 700;
	font-size: 14px;
	border: 1px solid transparent;
	cursor: pointer;
	transition: 0.2s;
}

.btn-primary {
	background: var(--primary);
	color: #fff;
}

.btn-primary:hover {
	filter: brightness(0.9);
}

.btn-sm {
	padding: 6px 12px;
	font-size: 13px;
}

.btn-outline {
	background: #fff;
	border-color: var(--border);
	color: var(--text);
}

.btn-outline:hover {
	background: #f9fafb;
}

.btn-danger {
	background: var(--danger);
	color: #fff;
}

.btn-danger:hover {
	background: #dc2626;
}

/* =========================
   TABLE & CARD
   ========================= */
.card {
	background: #fff;
	border: 1px solid var(--border);
	border-radius: 12px;
	box-shadow: var(--shadow);
	overflow: hidden;
}

.table {
	width: 100%;
	border-collapse: collapse;
	font-size: 14px;
}

.table th {
	background: #f9fafb;
	text-align: left;
	padding: 12px 16px;
	font-weight: 700;
	color: #4b5563;
	border-bottom: 1px solid var(--border);
}

.table td {
	padding: 12px 16px;
	border-bottom: 1px solid #f3f4f6;
	vertical-align: middle;
	color: #1f2937;
}

.table tr:hover {
	background: #f9fafb;
}

.table .title {
	font-weight: 600;
	color: #111827;
}

/* Helper classes */
.text-right {
	text-align: right;
}

.text-center {
	text-align: center;
}

/* Badges */
.badge {
	padding: 4px 8px;
	border-radius: 6px;
	font-size: 12px;
	font-weight: 600;
	background: #e5e7eb;
	color: #374151;
}

.pill {
	display: inline-block;
	padding: 4px 10px;
	border-radius: 99px;
	font-size: 12px;
	font-weight: 700;
}

.pill-green {
	background: #dcfce7;
	color: #166534;
}

.pill-gray {
	background: #f3f4f6;
	color: #9ca3af;
}

/* =========================
   PAGINATION
   ========================= */
.pagination {
	margin-top: 20px;
	display: flex;
	align-items: center;
	gap: 6px;
}

.page-btn {
	padding: 8px 12px;
	border: 1px solid var(--border);
	background: #fff;
	border-radius: 6px;
	text-decoration: none;
	color: var(--text);
	font-weight: 600;
	font-size: 13px;
}

.page-btn.active {
	background: var(--sidebar);
	color: #fff;
	border-color: var(--sidebar);
}

.page-meta {
	margin-left: auto;
	font-size: 13px;
	color: var(--muted);
}
</style>
</head>

<body>
	<div class="admin-layout">

		<jsp:include page="_sidebar.jsp" />

		<div class="content">

			<div class="page-header">
				<div>
					<h1>Danh sách đơn hàng</h1>
					<p class="sub">Quản lý và theo dõi trạng thái đơn hàng</p>
				</div>
			</div>

			<c:if test="${param.msg == 'success'}">
				<div class="alert alert-success">Cập nhật trạng thái thành
					công</div>
			</c:if>

			<div class="card">
				<div class="card-body">
					<table class="table">
						<thead>
							<tr>
								<th>ID</th>
								<th>Người nhận</th>
								<th>Số điện thoại</th>
								<th>Tổng tiền</th>
								<th>Trạng thái</th>
								<th>Phương thức</th>
								<th class="text-right">Hành động</th>
							</tr>
						</thead>

						<tbody>
							<c:forEach items="${orders}" var="o">
								<tr>
									<td>#${o.id}</td>
									<td class="title">${o.fullName}</td>
									<td>${o.phone}</td>
									<td><fmt:formatNumber value="${o.totalPrice}"
											type="currency" currencySymbol="₫" /></td>
									<td><c:choose>
											<c:when test="${o.status == 'PENDING'}">
												<span class="badge" style="background: #f59e0b; color: #fff">Chờ
													xử lý</span>
											</c:when>
											<c:when test="${o.status == 'SHIPPING'}">
												<span class="badge" style="background: #3b82f6; color: #fff">Đang
													giao</span>
											</c:when>
											<c:when test="${o.status == 'SUCCESS'}">
												<span class="badge" style="background: #10b981; color: #fff">Hoàn
													thành</span>
											</c:when>
											<c:when test="${o.status == 'CANCEL'}">
												<span class="badge" style="background: #ef4444; color: #fff">Đã
													hủy</span>
											</c:when>
											<c:otherwise>
												<span class="badge">${o.status}</span>
											</c:otherwise>
										</c:choose></td>
									<td>${o.paymentMethod}</td>

									<td class="text-right"><a class="btn btn-sm btn-outline"
										href="${pageContext.request.contextPath}/admin/order-detail?id=${o.id}">
											Xem chi tiết </a></td>
								</tr>
							</c:forEach>

							<c:if test="${empty orders}">
								<tr>
									<td colspan="7"
										style="padding: 14px; color: #6b7280; text-align: center;">
										Chưa có đơn hàng nào</td>
								</tr>
							</c:if>
						</tbody>
					</table>
				</div>
			</div>
			<c:if test="${totalPages > 1}">
				<div class="pagination">
					<c:choose>
						<c:when test="${currentPage > 1}">
							<a href="?page=${currentPage - 1}" class="page-btn">← Trước</a>
						</c:when>
						<c:otherwise>
							<span class="page-btn disabled">← Trước</span>
						</c:otherwise>
					</c:choose>

					<c:forEach begin="1" end="${totalPages}" var="i">
						<c:choose>
							<c:when test="${currentPage == i}">
								<span class="page-btn active">${i}</span>
							</c:when>
							<c:otherwise>
								<a href="?page=${i}" class="page-btn">${i}</a>
							</c:otherwise>
						</c:choose>
					</c:forEach>

					<c:choose>
						<c:when test="${currentPage < totalPages}">
							<a href="?page=${currentPage + 1}" class="page-btn">Sau →</a>
						</c:when>
						<c:otherwise>
							<span class="page-btn disabled">Sau →</span>
						</c:otherwise>
					</c:choose>

					<span class="page-meta">Trang ${currentPage} / ${totalPages}</span>
				</div>
			</c:if>
		</div>
	</div>
</body>
</html>