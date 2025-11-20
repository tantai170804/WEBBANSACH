<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ page import="model.Book" %>

<%
    Book book = (Book) request.getAttribute("book");
%>

<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Chi tiết sách</title>
</head>
<body>

<h1>📘 Chi tiết sách</h1>

<% if (book != null) { %>

    <h2><%= book.getTitle() %></h2>
    <p>Tác giả: <%= book.getAuthor() %></p>
    <p>Giá: <%= book.getPrice() %> đ</p>
    <p><%= book.getDescription() %></p>
    <p>
        <a href="cart?add=<%= book.getId() %>">🛒 Thêm vào giỏ</a>
    </p>

<% } else { %>
    <p>Không tìm thấy sách.</p>
<% } %>

</body>
</html>
