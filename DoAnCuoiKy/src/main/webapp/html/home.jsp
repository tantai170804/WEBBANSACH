<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
	<fmt:setLocale value="vi_VN"/>
	<div class="search-bar">
    	<input type="text" placeholder="Tìm kiếm sách...">
    	<button>Tìm Kiếm</button>
	</div>
    <h2>Danh sách sản phẩm</h2>
    <div class="book-list">
        <c:forEach var="book" items="${books}">
       		
            	<div class="book"> 
            		<a href="${pageContext.request.contextPath}/html/bookdetail?id=${book.bookId}">
            			<img src="${pageContext.request.contextPath}/book_img/${book.imageUrl}" alt="${book.title}" style="width: 100%; height: auto; border-radius: 5px;"> <!-- Hiển thị hình ảnh -->
                	</a>
                	<h3>${book.title}</h3>
                	<p>Tác giả: ${book.author}</p>
                
                	<p>Giá: <fmt:formatNumber value="${book.price}" type="currency" currencyCode="VND"/></p>
                	<p>Số lượng: ${book.quantityInStock}</p>
                	
            	</div>
            
        </c:forEach>
    </div>
</div>
<jsp:include page="footer.jsp" />

</body>
</html>