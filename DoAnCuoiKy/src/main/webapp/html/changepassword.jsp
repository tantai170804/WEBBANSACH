<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="vi">
<%@ taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<head>
<meta charset="UTF-8">
<style>
        body {
            background-color: #f8f9fa;
            color: #343a40;
        }

        .custom-navbar {
            background-color: #343a40;
        }

        .card {
            margin: 50px auto; 
            max-width: 400px; 
        }
        .form-label {
            font-weight: 600; /* Đậm hơn cho các nhãn */
        }
    </style>
<title>Đổi mật khẩu</title>
</head>
<body>
<jsp:include page="header.jsp" />
<main class="container">
        <div class="card">
            <div class="card-body">
                <h5 class="card-title text-center">Đổi Mật Khẩu</h5>
                <form id="changePasswordForm">
                    <div class="mb-3">
                        <label for="old-password" class="form-label">Mật khẩu cũ</label>
                        <input type="password" class="form-control" id="old-password" placeholder="Nhập mật khẩu cũ" required>
                    </div>
                    <div class="mb-3">
                        <label for="new-password" class="form-label">Mật khẩu mới</label>
                        <input type="password" class="form-control" id="new-password" placeholder="Nhập mật khẩu mới" required>
                    </div>
                    <div class="mb-3">
                        <label for="confirm-password" class="form-label">Xác nhận mật khẩu</label>
                        <input type="password" class="form-control" id="confirm-password" placeholder="Xác nhận mật khẩu mới" required>
                    </div>
                    <button type="submit" class="btn btn-primary w-100 change-pass">Đổi Mật Khẩu</button>
                </form>
            </div>
        </div>
    </main>


</body>
<jsp:include page="footer.jsp" />
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/js/bootstrap.min.js"></script>
<script>
document.getElementById("changePasswordForm").addEventListener("submit", function (e) {
    e.preventDefault(); 

    const oldPassword = document.getElementById("old-password").value.trim();
    const newPassword = document.getElementById("new-password").value.trim();
    const confirmPassword = document.getElementById("confirm-password").value.trim();
        fetch("${pageContext.request.contextPath}/changepassword?password="+oldPassword+"&newpassword="+newPassword+"&confirmpassword="+confirmPassword, {
            method: "POST"
        })
        .then(res => res.json())
        .then(data => {
            showToast(data.message);
        });
    });

</script>
</html>