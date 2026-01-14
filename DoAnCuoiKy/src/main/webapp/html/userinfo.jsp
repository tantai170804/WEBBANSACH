<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
 	<meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <style>
        body {
            background-color: #f8f9fa;
            color: #343a40;
        }

        .custom-navbar {
            background-color: #343a40;
        }

        .card {
            margin-top: 20px;
        }

        .user-info {
            display: flex;
            align-items: center;
            margin-bottom: 10px;
        }

        .user-info i {
            margin-right: 10px;
            color: #007bff;
        }

        .user-info span {
            font-weight: 500;
        }
    </style>
    <title>Thông tin người dùng</title>
<body>
<jsp:include page="header.jsp" />
<main class="container">
        <div class="card">
            <div class="card-body">
                <h5 class="card-title">Thông tin người dùng</h5>
                
                <div class="user-info">
                    <i class="bi bi-person-circle"></i>
                    <span>ID người dùng: ${sessionScope.currentUser.userId}</span>
                </div>
                <div class="user-info">
                    <i class="bi bi-person-fill"></i>
                    <span>Tên: ${sessionScope.currentUser.userName}</span>
                </div>
                <div class="user-info">
                    <i class="bi bi-envelope-fill"></i>
                    <span>Email: ${sessionScope.currentUser.email}</span>
                </div>
                <div class="user-info">
                    <i class="bi bi-phone-fill"></i>
                    <span>Điện thoại: ${sessionScope.currentUser.phone}</span>
                </div>
                <div class="user-info">
                    <i class="bi bi-house-fill"></i>
                    <span>Địa chỉ: ${sessionScope.currentUser.address}</span>
                </div>
                <div class="user-info">
                    <i class="bi bi-person-badge-fill"></i>
                    <span>Vai trò: ${sessionScope.currentUser.role}</span>
                </div>
                <div class="user-info">
                    <i class="bi bi-calendar-fill"></i>
                    <span>Ngày tạo: ${sessionScope.currentUser.createAt}</span>
                </div>
            </div>
        </div>
    </main>
    <jsp:include page="footer.jsp" />
</body>
</html>