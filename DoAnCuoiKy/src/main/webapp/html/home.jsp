<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Trang Chủ - Cửa Hàng Bán Sách</title>
    <link href="../css/styles.css" rel="stylesheet" type="text/css" > <!-- Liên kết file CSS -->
</head>
<jsp:include page="header.jsp" />
<body>
<div class="container">
    <h2>Sách Nổi Bật</h2>
    <div class="book-list">
        <c:forEach var="book" items="${books}">
            <div class="book">
                <h3>${book.title}</h3>
                <p>Tác giả: ${book.author}</p>
                <p>Giá: ${book.price} $</p>
            </div>
        </c:forEach>
    </div>
</div>
<jsp:include page="footer.jsp" />

</body>
</html>