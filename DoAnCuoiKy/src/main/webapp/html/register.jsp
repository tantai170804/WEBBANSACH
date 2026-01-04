<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Đăng ký</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/auth.css">
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
