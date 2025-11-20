<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="model.Book" %>

<%
    List<Book> cart = (List<Book>) session.getAttribute("cart");
%>

<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Giỏ hàng</title>
</head>
<body>

<h1>🛒 Giỏ hàng</h1>

<%
    if (cart == null || cart.isEmpty()) {
%>
    <p>Giỏ hàng của bạn đang trống.</p>
<%
    } else {
%>
<ul>
<%
        for (Book b : cart) {
%>
    <li>
        <%= b.getTitle() %> – <%= b.getPrice() %> đ
        <a href="cart?remove=<%= b.getId() %>">❌ Xóa</a>
    </li>
<%
        }
%>
</ul>

<p><a href="checkout">Thanh toán</a></p>

<%
    }
%>

</body>
</html>
