<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Đăng nhập</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/auth.css">
</head>
<body>

<div class="auth-wrap">
  <div class="auth-card">

    <div class="auth-header">
      <span class="auth-brand">BOOK STORE</span>
      <h1 class="auth-title">Đăng nhập</h1>
      <p class="auth-sub">Vui lòng nhập tài khoản để tiếp tục</p>
    </div>

    <div class="auth-body">
      <c:if test="${not empty error}">
        <div class="alert alert-danger">
          <c:out value="${error}" />
        </div>
      </c:if>

      <form class="form" method="post" action="${pageContext.request.contextPath}/login">
        <div class="field">
          <label>Tên đăng nhập</label>
          <input class="input"
                 type="text"
                 name="username"
                 placeholder="Nhập tên đăng nhập"
                 value="${username}"
                 required />
        </div>

        <div class="field">
          <label>Mật khẩu</label>
          <input class="input"
                 type="password"
                 name="password"
                 placeholder="Nhập mật khẩu"
                 required
                 autocomplete="current-password" />
        </div>

        <div class="row">
          <a class="link" href="${pageContext.request.contextPath}/forgot-password">
            Quên mật khẩu?
          </a>
          <a class="link" href="${pageContext.request.contextPath}/register">
            Đăng ký
          </a>
        </div>

        <button class="btn btn-primary" type="submit">Đăng nhập</button>

        <div class="divider">hoặc</div>

        <a class="btn btn-outline" href="${pageContext.request.contextPath}/html/home">
          ← Về trang chủ
        </a>
      </form>
    </div>

    <div class="auth-footer">
      Chưa có tài khoản? <a class="link" href="${pageContext.request.contextPath}/register">Đăng ký ngay</a>
    </div>

  </div>
</div>

</body>
</html>
