<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<head>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.css">      
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css">   
<link rel="stylesheet"
      href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css" />

<link href="${pageContext.request.contextPath}/css/head.css" rel="stylesheet" type="text/css" >
</head>
<header>
 <nav class="navbar navbar-expand-lg navbar-dark custom-navbar">
    <div class="container-fluid">
      <a class="navbar-brand fw-bold" href="#">
          <i class="bi bi-book"></i> Web bán sách
      </a>
      <a class="navbar-brand" href="#"><i class="bi bi-house-door-fill me-1"></i></a>
      
      <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>
      
      <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav me-auto mb-2 mb-lg-0">
          <li class="nav-item">
            <a class="nav-link active" aria-current="page" href="${pageContext.request.contextPath}/html/home">Home</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="${pageContext.request.contextPath}/randombook"><i class="fas fa-random me-1"></i>Sách ngẫu nhiên</a>
          </li>
        </ul>

        <form class="d-flex align-items-center gap-2">
          <c:if test="${not empty sessionScope.currentUser}">
            <!-- Giỏ hàng -->
            <a href="${pageContext.request.contextPath}/cart"
               class="btn btn-light position-relative">
              <i class="fa fa-shopping-cart me-1"></i>
              Giỏ hàng
              <c:if test="${sessionScope.numberInCart > 0}">
              <span class="position-absolute top-0 start-100 translate-middle badge rounded-pill bg-danger">
                ${sessionScope.numberInCart}
              </span>
              </c:if>
            </a>
          </c:if>

          <!-- Tài khoản -->
          <div class="dropdown">
            <a href="#"
               class="btn btn-light dropdown-toggle"
               data-bs-toggle="dropdown">
              <i class="fa-solid fa-user me-1"></i>
              Tài khoản
            </a>

            <ul class="dropdown-menu dropdown-menu-end">
              <c:if test="${empty sessionScope.currentUser}">
                <li>
                <a href="${pageContext.request.contextPath}/login" class="dropdown-item">
                 <i class="fa fa-sign-in me-1"></i> Đăng nhập</a>
                 </li>
              </c:if>
              <c:if test="${not empty sessionScope.currentUser}">
                <li><a href="${pageContext.request.contextPath}/userinfo" class="dropdown-item"><i class="fa fa-info-circle me-1"></i> Thông tin</a></li>
                <li><a href="${pageContext.request.contextPath}/orderlist" class="dropdown-item"><i class="fa-solid fa-newspaper me-1"></i> Xem đơn hàng</a></li>
                <li><a href="${pageContext.request.contextPath}/changepassword" class="dropdown-item"><i class="fa-solid fa-key me-1"></i> Đổi mật khẩu</a></li>
                <c:if test="${sessionScope.currentUser.role == 'admin'}">
                <li><a href="${pageContext.request.contextPath}/admin/dashboard" class="dropdown-item"><i class="fas fa-user-tie me-1"></i> Quản lý sách</a></li>	
                </c:if>
                <li><hr class="dropdown-divider"></li>
                <li><a href="${pageContext.request.contextPath}/logout" class="dropdown-item">
                <i class="fa fa-sign-out me-1"></i> Đăng xuất</a></li>
              </c:if>
            </ul>
          </div>
        </form>
      </div>
    </div>
  </nav>
</header>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
<script>
document.querySelectorAll('.navbar .dropdown').forEach(function (dropdown) {
  dropdown.addEventListener('mouseenter', function () {
    let menu = this.querySelector('.dropdown-menu');
    let toggle = this.querySelector('.dropdown-toggle');
    menu.classList.add('show');
    toggle.classList.add('show');
  });

  dropdown.addEventListener('mouseleave', function () {
    let menu = this.querySelector('.dropdown-menu');
    let toggle = this.querySelector('.dropdown-toggle');
    menu.classList.remove('show');
    toggle.classList.remove('show');
  });
});
</script>