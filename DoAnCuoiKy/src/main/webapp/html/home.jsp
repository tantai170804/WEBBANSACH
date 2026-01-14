<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Trang Chủ - Cửa Hàng Bán Sách</title>
   <style>
        /* CSS tùy chỉnh cho navbar */
        body {
            background-color: #f8f9fa;
            color: #343a40;
        }
        .custom-navbar {
            background-color: #343a40;
        }
        .navbar-nav .nav-link {
            margin-right: 15px;
            border-radius: 5px;
        }
        .navbar-nav .nav-link:hover {
            background-color: #495057;
            color: #ffffff;	
        }
        .dropdown-menu {
            min-width: 150px;
        }

        /* CSS cho sản phẩm sách */
        .book-card {
            border: none;
            border-radius: 10px;
            transition: 0.25s;
            margin-bottom: 20px;
        }

        .book-card:hover {
            transform: translateY(-4px);
            box-shadow: 0 8px 20px rgba(0,0,0,0.12);
        }

        .book-img {
            height: 260px;
            object-fit: contain;
            padding: 10px;
            display: block; /* Căn giữa */
            margin: 0 auto; /* Căn giữa */
        }

        .book-title {
            font-size: 20px;
            font-weight: 500;
            line-height: 1.4;
        }

        .price {
            font-size: 20px;
            font-weight: 700;
            color: #d70018;
        }

        .sale-badge {
            background: #d70018;
            font-size: 14px;
        }

        .old-price {
            text-decoration: line-through;
            color: #9e9e9e;
        }

        .star {
            color: #f6a500;
        }
    </style>
    
</head>

<body>
	<jsp:include page="header.jsp" />
	
	<div class="container my-4">
		<form action="search" method="get" class="d-flex justify-content-center mb-3">
  <div class="input-group input-group-sm" style="max-width: 600px;">
    <input type="text"
           name="keyword"
           class="form-control"
           placeholder="Tìm sách..."
           value="${param.keyword}">
    <button class="btn btn-primary" type="submit">
      <p><i class="bi bi-search"></i> Tìm kiếm</p>
    </button>
  </div>
</form>
		
     <h2>Danh sách sản phẩm</h2>
        	<div class="row">
   				<c:forEach var="bookview" items="${bookviews}">
       		
            	 <div class="col-md-3">
            	 <div class="card book-card">
            		<a href="${pageContext.request.contextPath}/bookdetail?id=${bookview.book.bookId}">
            			<img src="${pageContext.request.contextPath}/book_img/${bookview.book.imageUrl}" alt="${bookview.book.title}" class="book-img">
                	</a>
                	<div class="card-body">
                	<h5 class="card-title book-title">${bookview.book.title}</h5>
                	<p>Tác giả: ${bookview.book.author}</p>
                
                	<p class="price"><fmt:formatNumber value="${bookview.book.price}" type="currency" currencyCode="VND"/></p>
                	
                	<button type="button"
        class="btn btn-success btn-sm"
        data-bs-toggle="modal"
        data-bs-target="#addToCartModal"
        onclick="openAddToCartModal(
            '${bookview.book.bookId}',
            '${bookview.book.title}',
            ${bookview.inCart}
        )">
    <i class="fa fa-cart-plus"></i> Thêm vào giỏ
</button>

                	<a class="btn btn-primary btn-sm ml-1" href="${pageContext.request.contextPath}/bookdetail?id=${bookview.book.bookId}">
                			<i class="fa fa-info-circle" aria-hidden="true"></i> Xem chi tiết
                	</a>
                	</div>
            	</div>
            </div>
        </c:forEach>
        
    		</div>
	</div>
<jsp:include page="footer.jsp" />
<div class="modal fade" id="addToCartModal" tabindex="-1">
  <div class="modal-dialog modal-dialog-centered">
    <div class="modal-content">

      <form action="${pageContext.request.contextPath}/cart" method="post">

        <div class="modal-header">
          <h5 class="modal-title">Thêm vào giỏ hàng</h5>
          <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
        </div>

        <div class="modal-body">
          <p id="bookTitle"></p>

          <p id="cartInfo"></p>
          
          
          <label>Số lượng:</label>
          <input type="number"
                 class="form-control"
                 id="quantity"
                 value="1"
                 min="1">

          <input type="hidden" name="action" value="add">
          <input type="hidden" name="bid" id="bookIdInput">
        </div>

        <div class="modal-footer">
         <button type="button" class="btn btn-success btn-sm" onclick="addToCart()">
        <i class="fa fa-cart-plus"></i> Thêm vào giỏ
</button>
         

          <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">
            Hủy
          </button>
        </div>

      </form>

    </div>
  </div>
</div>
</body>

<script>
function openAddToCartModal(bookId, title) {
    document.getElementById("bookIdInput").value = bookId;
    document.getElementById("bookTitle").innerText = title;
    if(inCart){
   	 document.getElementById("cartInfo").innerText = "Sách hiện đã có trong giỏ, xem tại phần giỏ hàng";
   } else {
   	 document.getElementById("cartInfo").innerText = "Sách hiện chưa có trong giỏ";
   }
}
function addToCart() {
	const bookId = document.getElementById("bookIdInput").value;
	const quantity = document.getElementById("quantity").value;
	if(!quantity || quantity <= 0){
		showToast("Số lượng không hợp lệ hoặc ít hơn 1, hãy nhập lại");
		return;
	} 
	fetch("${pageContext.request.contextPath}/cart?action=add&bid="+bookId+"&quantity="+quantity, {
        method: "POST"
    })
    .then(res => res.json())
    .then(data => {
    	console.log("Type message: "+data.type);
    	if(data.type != 2){
    		const modalEl = document.getElementById('addToCartModal');
        	const modalInstance = bootstrap.Modal.getInstance(modalEl);
        	modalInstance.hide();
    	}
    	showToast(data.message);
    })
    .catch(() => {
        showToast('Bạn phải tiếp tục đăng nhập để làm điều đó');
    });
}
</script>

</html>
