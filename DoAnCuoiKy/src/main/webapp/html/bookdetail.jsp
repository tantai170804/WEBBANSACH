<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Chi Tiết Sách - ${book.title}</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
    <link href="../css/detail_book.css" rel="stylesheet" type="text/css" > <!-- Liên kết file CSS -->
</head>
<body>
<jsp:include page="header.jsp" />
<fmt:setLocale value="vi_VN"/>
<div class="search-bar">
    <input type="text" placeholder="Tìm kiếm sách...">
    <button>Tìm Kiếm</button>
</div> 
<div class="details">
    <div class="book-info">
        <h2>${book.title}</h2>
        <p>Tác giả: ${book.author}</p>
        <p>Nhà xuất bản: ${book.publisher}</p>
        <p>Số lượng: ${book.quantityInStock}</p>
        <p>Mô tả:</p>
        <p>${book.description}</p>
        <p class="price">Giá: <fmt:formatNumber value="${book.price}" type="currency" currencyCode="VND"/></p>
        <button>Thêm vào giỏ</button>
    </div>
    <div class="book-image">
   <img src="${pageContext.request.contextPath}/book_img/${book.imageUrl}" alt="${book.title}"> <!-- Hiển thị hình ảnh -->
	</div>
</div>

<jsp:include page="footer.jsp" />

</body>
</html>