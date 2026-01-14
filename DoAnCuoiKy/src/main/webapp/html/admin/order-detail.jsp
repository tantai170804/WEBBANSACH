<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<c:set var="activePage" value="orders" scope="request" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Chi tiết đơn hàng #${order.id}</title>
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
					<h1>Chi tiết đơn hàng ${order.id}</h1>
					<p class="sub">Xem thông tin và cập nhật trạng thái</p>
				</div>
				<a href="${pageContext.request.contextPath}/admin/orders"
					class="btn btn-outline"> ← Quay lại danh sách </a>
			</div>

			<div class="grid-2">

				<div class="card">
					<div class="card-body">
						<h3 style="margin-bottom: 15px; font-size: 18px;">Thông tin
							nhận hàng</h3>

						<div class="info-row">
							<span class="info-label">Người nhận:</span> ${order.fullName}
						</div>
						<div class="info-row">
							<span class="info-label">Số điện thoại:</span> ${order.phone}
						</div>
						<div class="info-row">
							<span class="info-label">Địa chỉ:</span> ${order.address}
						</div>
						<div class="info-row">
							<span class="info-label">Phương thức:</span>
							${order.paymentMethod}
						</div>
						<div class="info-row"
							style="margin-top: 15px; padding-top: 10px; border-top: 1px solid #eee;">
							<span class="info-label">Tổng thanh toán:</span> <span
								style="color: #ef4444; font-weight: bold; font-size: 1.2em;">
								<fmt:formatNumber value="${order.totalPrice}" type="currency"
									currencySymbol="₫" />
							</span>
						</div>
					</div>
				</div>

				<div class="card">
					<div class="card-body">
						<h3 style="margin-bottom: 15px; font-size: 18px;">Cập nhật
							trạng thái</h3>

						<form
							action="${pageContext.request.contextPath}/admin/order-update"
							method="POST">
							<input type="hidden" name="id" value="${order.id}"> <label
								style="display: block; margin-bottom: 8px; color: #666;">Trạng
								thái hiện tại:</label> <select name="status" class="form-select">
								<option value="0"
									${order.status == 'PENDING' ? 'selected' : ''}>Chờ xử
									lý (PENDING)</option>
								<option value="1"
									${order.status == 'SHIPPING' ? 'selected' : ''}>Đang
									giao hàng (SHIPPING)</option>
								<option value="2"
									${order.status == 'SUCCESS' ? 'selected' : ''}>Hoàn
									thành (SUCCESS)</option>
								<option value="3"
									${order.status == 'CANCEL' ? 'selected' : ''}>Đã hủy
									(CANCEL)</option>
							</select>

							<button type="submit" class="btn btn-primary"
								style="background: #3b82f6; color: white;">Cập nhật
								ngay</button>
						</form>
					</div>
				</div>
			</div>

			<div class="card">
				<div class="card-body">
					<h3 style="margin-bottom: 15px; font-size: 18px;">Sản phẩm đã
						mua</h3>

					<table class="table">
						<thead>
							<tr>
								<th style="width: 80px;">Hình ảnh</th>
								<th>Tên sách</th>
								<th>Đơn giá</th>
								<th>Số lượng</th>
								<th>Thành tiền</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${items}" var="i">
								<tr>
									<td><img
										src="${pageContext.request.contextPath}/book_img/${i.bookImage}"
										class="book-thumb"
										onerror="this.src='https://placehold.co/50x70?text=NoImg'">
									</td>
									<td class="title" style="vertical-align: middle;">${i.bookName}</td>
									<td style="vertical-align: middle;"><fmt:formatNumber
											value="${i.price}" type="currency" currencySymbol="₫" /></td>
									<td style="vertical-align: middle;">${i.quantity}</td>
									<td style="vertical-align: middle; font-weight: bold;"><fmt:formatNumber
											value="${i.price * i.quantity}" type="currency"
											currencySymbol="₫" /></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>

		</div>
	</div>
</body>
</html>