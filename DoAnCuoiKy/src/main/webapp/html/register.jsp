<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Đăng ký</title>
<style>/* =========================
   AUTH (LOGIN / REGISTER / FORGOT)
   ========================= */
:root {
	--bg: #f5f7fb;
	--card: #ffffff;
	--text: #111827;
	--muted: #6b7280;
	--primary: #2563eb;
	--primary-hover: #1d4ed8;
	--border: #e5e7eb;
	--shadow: 0 12px 30px rgba(0, 0, 0, .08);
	--danger-bg: #fee2e2;
	--danger-text: #991b1b;
	--danger-border: #fecaca;
	--success-bg: #dcfce7;
	--success-text: #166534;
	--success-border: #bbf7d0;
}

* {
	box-sizing: border-box;
}

body {
	margin: 0;
	font-family: Arial, sans-serif;
	background: var(--bg);
	color: var(--text);
}

/* Center wrapper */
.auth-wrap {
	min-height: 100vh;
	display: flex;
	align-items: center;
	justify-content: center;
	padding: 24px;
}

/* Card */
.auth-card {
	width: 100%;
	max-width: 420px;
	background: var(--card);
	border: 1px solid var(--border);
	border-radius: 16px;
	box-shadow: var(--shadow);
	overflow: hidden;
}

/* Header */
.auth-header {
	padding: 18px 18px 0;
}

.auth-brand {
	font-weight: 900;
	letter-spacing: .5px;
	display: inline-block;
	padding: 8px 10px;
	border-radius: 12px;
	background: rgba(37, 99, 235, .10);
	color: var(--primary);
	font-size: 13px;
}

.auth-title {
	margin: 10px 0 0;
	font-size: 22px;
	font-weight: 900;
}

.auth-sub {
	margin: 6px 0 0;
	color: var(--muted);
	font-size: 13px;
}

/* Body */
.auth-body {
	padding: 16px 18px 18px;
}

/* Alerts */
.alert {
	padding: 10px 12px;
	border-radius: 12px;
	font-size: 13px;
	font-weight: 800;
	margin-bottom: 12px;
}

.alert-danger {
	background: var(--danger-bg);
	color: var(--danger-text);
	border: 1px solid var(--danger-border);
}

.alert-success {
	background: var(--success-bg);
	color: var(--success-text);
	border: 1px solid var(--success-border);
}

/* Form */
.form {
	display: grid;
	gap: 12px;
}

.field label {
	display: block;
	font-size: 13px;
	font-weight: 800;
	margin-bottom: 6px;
}

.input {
	width: 100%;
	padding: 11px 12px;
	border: 1px solid var(--border);
	border-radius: 12px;
	outline: none;
	font-size: 14px;
	background: #fff;
}

.input:focus {
	border-color: rgba(37, 99, 235, .5);
	box-shadow: 0 0 0 4px rgba(37, 99, 235, .12);
}

/* Row utils */
.row {
	display: flex;
	align-items: center;
	justify-content: space-between;
	gap: 10px;
	flex-wrap: wrap;
}

.small {
	font-size: 13px;
	color: var(--muted);
}

/* Buttons */
.btn {
	display: inline-flex;
	align-items: center;
	justify-content: center;
	gap: 8px;
	width: 100%;
	padding: 11px 12px;
	border-radius: 12px;
	border: 1px solid transparent;
	cursor: pointer;
	font-weight: 900;
	font-size: 14px;
	text-decoration: none;
	user-select: none;
}

.btn-primary {
	background: var(--primary);
	color: #fff;
}

.btn-primary:hover {
	background: var(--primary-hover);
}

.btn-outline {
	background: #fff;
	border-color: var(--border);
	color: var(--text);
}

.btn-outline:hover {
	background: #f3f4f6;
}

/* Links */
.link {
	color: var(--primary);
	font-weight: 900;
	text-decoration: none;
}

.link:hover {
	text-decoration: underline;
}

/* Divider */
.divider {
	display: flex;
	align-items: center;
	gap: 10px;
	margin: 12px 0;
	color: var(--muted);
	font-size: 12px;
	font-weight: 800;
}

.divider::before, .divider::after {
	content: "";
	height: 1px;
	background: var(--border);
	flex: 1;
}

/* Footer */
.auth-footer {
	padding: 0 18px 18px;
	text-align: center;
	color: var(--muted);
	font-size: 13px;
	font-weight: 700;
}

/* Responsive */
@media ( max-width : 420px) {
	.auth-header, .auth-body, .auth-footer {
		padding-left: 14px;
		padding-right: 14px;
	}
}
</style>
</head>

<body>
	<div class="auth-wrap">
		<div class="auth-card">

			<div class="auth-header">
				<span class="auth-brand">BOOK STORE</span>
				<h1 class="auth-title">Đăng ký</h1>
				<p class="auth-sub">Tạo tài khoản mới để sử dụng hệ thống</p>
			</div>

			<div class="auth-body">
				<c:if test="${not empty error}">
					<div class="alert alert-danger">
						<c:out value="${error}" />
					</div>
				</c:if>

				<form class="form" method="post"
					action="${pageContext.request.contextPath}/register">

					<div class="field">
						<label>Tên đăng nhập</label> <input class="input" type="text"
							name="username" placeholder="Nhập tên đăng nhập"
							value="${username}" required />
					</div>

					<div class="field">
						<label>Mật khẩu</label> <input class="input" type="password"
							name="password" placeholder="Nhập mật khẩu" required
							autocomplete="new-password" />
					</div>

					<div class="field">
						<label>Xác nhận mật khẩu</label> <input class="input"
							type="password" name="confirmPassword"
							placeholder="Nhập lại mật khẩu" required
							autocomplete="new-password" />
					</div>

					<button class="btn btn-primary" type="submit">Đăng ký</button>

					<div class="divider">hoặc</div>

					<a class="btn btn-outline"
						href="${pageContext.request.contextPath}/login"> Đăng nhập </a>
				</form>
			</div>

			<div class="auth-footer">
				Đã có tài khoản? <a class="link"
					href="${pageContext.request.contextPath}/login">Đăng nhập</a>
			</div>

		</div>
	</div>
</body>
</html>
