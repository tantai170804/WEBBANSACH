<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Chi Tiết Sách - Tựa Sách 1</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f8f9fa;
        }
        header {
            background-color: #343a40;
            color: white;
            padding: 20px;
            text-align: center;
        }
        .search-bar {
            margin: 20px auto;
            max-width: 600px;
            display: flex;
        }
        .search-bar input {
            flex: 1;
            padding: 10px;
            border: 1px solid #ddd;
            border-radius: 5px;
        }
        .search-bar button {
            padding: 10px;
            background-color: #28a745;
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }
        .details {
            display: flex;
            max-width: 1200px;
            margin: auto;
            padding: 20px;
        }
        .book-info {
            flex: 1;
            padding: 20px;
            background-color: white;
            border-radius: 5px;
            box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
            margin-right: 20px;
        }
        .book-info h2 {
            margin-top: 0;
        }
        .price {
            color: #28a745;
            font-size: 1.5em;
        }
        .book-image {
            max-width: 300px;
            margin-left: 20px;
        }
        footer {
            text-align: center;
            padding: 20px;
            background-color: #343a40;
            color: white;
            position: relative;
            bottom: 0;
            width: 100%;
        }
        .icons {
            position: absolute;
            right: 20px;
            top: 20px;
        }
        .icons i {
            color: white;
            margin-left: 15px;
            cursor: pointer;
        }
    </style>
</head>
<body>

<header>
    <h1>Chi Tiết Sách</h1>
    <div class="icons">
        <i class="fas fa-shopping-cart"></i>
        <i class="fas fa-user"></i>
    </div>
</header>

<div class="search-bar">
    <input type="text" placeholder="Tìm kiếm sách...">
    <button>Tìm Kiếm</button>
</div>

<div class="details">
    <div class="book-info">
        <h2>Tựa Sách 1</h2>
        <p>Tác giả: Tác giả 1</p>
        <p>Thể loại: Tiểu thuyết</p>
        <p>Mô tả: Đây là một mô tả ngắn gọn về cuốn sách để thu hút độc giả.</p>
        <p class="price">Giá: 150.000 VNĐ</p>
        <button>Đặt Hàng</button>
    </div>
    <img src="https://via.placeholder.com/300" alt="Hình ảnh Tựa Sách 1" class="book-image">
</div>

<footer>
    <p>&copy; 2025 Cửa Hàng Bán Sách. Mọi quyền được bảo lưu.</p>
</footer>

</body>
</html>