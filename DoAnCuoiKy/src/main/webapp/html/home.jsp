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
</head>
<jsp:include page="header.jsp" />
<jsp:include page="/WEB-INF/common/snackbar.jsp"/>
<body>
<div class="container">
	<fmt:setLocale value="vi_VN"/>
	<div class="search-bar">
    	<input type="text" placeholder="Tìm kiếm sách...">
    	<button>Tìm Kiếm</button>
	</div>
    <h2>Danh sách sản phẩm</h2>
    <div class="book-list">
        <c:forEach var="bookview" items="${bookviews}">
       		
            	<div class="book"> 
            		<a href="${pageContext.request.contextPath}/bookdetail?id=${bookview.book.bookId}">
            			<img src="${pageContext.request.contextPath}/book_img/${bookview.book.imageUrl}" alt="${bookview.book.title}" style="width: 100%; height: auto; border-radius: 5px;"> <!-- Hiển thị hình ảnh -->
                	</a>
                	<h3>${bookview.book.title}</h3>
                	<p>Tác giả: ${bookview.book.author}</p>
                
                	<p>Giá: <fmt:formatNumber value="${bookview.book.price}" type="currency" currencyCode="VND"/></p>
                	<p>Số lượng: ${bookview.book.quantityInStock}</p>
                	<div class="row">
                		
                		
                		<form action="${pageContext.request.contextPath}/cart" method="post" class="d-inline">
                		<c:if test="${!bookview.inCart}">  
                			<input type="hidden" name="action" value="add">
                			<input type="hidden" name="bid" value="${bookview.book.bookId}">
                			<button type="submit" class="btn btn-success btn-sm ml-1">
                			Thêm vào giỏ
                			</button>
                		</c:if>
                		<c:if test="${bookview.inCart}">  
                			<input type="hidden" name="action" value="delete">
                			<input type="hidden" name="bid" value="${bookview.book.bookId}">
                			<button type="submit" class="btn btn-danger btn-sm ml-1">
                			Xóa khỏi giỏ
                			</button>
                		</c:if>
                		</form>
                		
                		
                		<a href="" class="btn btn-primary btn-sm ml-1">Xem chi tiết</a>
                		
                	</div>
            	</div>
            
        </c:forEach>
    </div>
</div>
<jsp:include page="footer.jsp" />

</body>
</html>