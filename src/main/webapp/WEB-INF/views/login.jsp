<%@ page language="java" contentType="text/html; charset=UTF-8"%>

<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Đăng nhập</title>
</head>
<body>

<h1>🔐 Đăng nhập</h1>

<form action="login" method="post">
    <label>Email:</label><br>
    <input type="text" name="email"><br><br>

    <label>Mật khẩu:</label><br>
    <input type="password" name="password"><br><br>

    <button type="submit">Đăng nhập</button>
</form>

<p>Chưa có tài khoản? <a href="register">Đăng ký</a></p>

</body>
</html>
