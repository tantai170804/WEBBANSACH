<%@ page language="java" contentType="text/html; charset=UTF-8"%>

<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Đăng ký</title>
</head>
<body>

<h1>📝 Đăng ký tài khoản</h1>

<form action="register" method="post">

    <label>Họ tên:</label><br>
    <input type="text" name="name"><br><br>

    <label>Email:</label><br>
    <input type="text" name="email"><br><br>

    <label>Mật khẩu:</label><br>
    <input type="password" name="password"><br><br>

    <button type="submit">Đăng ký</button>
</form>

<p>Đã có tài khoản? <a href="login">Đăng nhập</a></p>

</body>
</html>
