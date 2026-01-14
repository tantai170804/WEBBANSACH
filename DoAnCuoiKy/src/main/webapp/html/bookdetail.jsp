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
     <style>
        body {
            background-color: #f8f9fa;
            color: #343a40;
        }

        .custom-navbar {
            background-color: #343a40;
        }

        .card {
            margin-top: 20px;
        }

        .book-img {
            height: 500px;
            object-fit: contain;
            padding: 10px;
        }

        .price {
            font-size: 24px;
            font-weight: 700;
            color: #d70018;
        }

        .description {
            margin-top: 10px;
            line-height: 1.6;
        }
    </style>
</head>
<body>
<jsp:include page="header.jsp" />
<fmt:setLocale value="vi_VN"/>
<div class="container">
<div class="card">
            <div class="card-body">
        <div class="row">
            <div class="col-md-6">
                <h1 class="book-title">${book.title}</h1>
                <h5><span class="text-muted">Tác giả: ${book.author}</span></h5>
                <h5><span class="text-muted">Nhà xuất bản: ${book.publisher}</span></h5>
                <h5><span class="text-muted">Mô tả:</span></h5>
                <p class="description">${book.description}</p>
                <p class="price">Giá: <fmt:formatNumber value="${book.price}" type="currency" currencyCode="VND"/></p>
                <button type="button"
        class="btn btn-success btn-sm"
        data-bs-toggle="modal"
        data-bs-target="#addToCartModal"
        onclick="openAddToCartModal(
            '${book.bookId}',
            '${book.title}'
        )">
    <i class="fa fa-cart-plus"></i> Thêm vào giỏ
</button>
            </div>
            <div class="col-md-6">
                <img src="${pageContext.request.contextPath}/book_img/${book.imageUrl}" alt="${book.title}" class="book-img">
            </div>
        </div>
        </div>
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
        <i class="fa fa-cart-plus"></i> Thêm vào giỏ</button>
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
}
function addToCart() {
	const bookId = document.getElementById("bookIdInput").value;
	const quantity = document.getElementById("quantity").value;
	if(!quantity || quantity <= 0){
		showToast("Số lượng không hợp lệ hoặc ít hơn 1, hãy nhập lại");
		return;
	} else {
		console.log("Bookid: "+bookId);
		console.log("Số lượng: "+quantity);
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