<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html lang="vi">
<%@ taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Giỏ Hàng</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            background-color: #f4f4f4;
        }
        .cart-container {
            margin: 20px;
        }
        .cart-item img {
            max-width: 70px;
            max-height: auto;
            border-radius: 4px;
        }
        .payment-method {
    		display: none;
		}
       
    </style>
     <jsp:include page="header.jsp" />
</head>
<body>

<div class="container cart-container bg-white rounded shadow">
    <div class="row">
    <fmt:setLocale value="vi_VN"/>
        <div class="col-md-8 p-4">
            <h2>Giỏ Hàng Của Bạn</h2>
             <c:forEach var="cartitem" items="${cartItems}" varStatus="st">
            <div class="row my-3 border-bottom pb-3 cart-item" data-bid="${cartitem.bookId}">
                <div class="col-2">
                    <img src="${pageContext.request.contextPath}/book_img/${cartitem.bookImageUrl}" class="img-thumbnail">
                </div>
                <div class="col-6">
                    <p><strong>Tên sách: </strong>${cartitem.bookTitle}</p>
                    <p>
                    
                        <strong>Số lượng:</strong>
                        <input type="number" class="form-control d-inline w-25 quantity-input" value="${cartitem.quantity}" min="1"/>
                    </p>
                    <p>
                        <strong>Tổng cộng giá: 
                         <span class="item-total">
                        <fmt:formatNumber value="${cartitem.totalPrice}" type="currency"/>
                        </span>
                        </strong>
                    </p>
                    <button type="button" class="btn btn-warning btn-sm ms-1 btn-update">Cập nhật</button>
                    <button type="button" class="btn btn-danger btn-sm ms-2 btn-remove">Loại Bỏ</button>
                </div>
            </div>
            </c:forEach>
        </div>

        <div class="col-md-4 p-4 border-start">
            <h2>Bảng Thanh Toán</h2>
            <p>Tổng cộng: 
            <span>
            <strong class="cart-total"><fmt:formatNumber value="${cartTotalBookPrice}" type="currency"/></strong>
            </span>
            </p>
            <p>Phí vận chuyển: <strong><fmt:formatNumber value="30000" type="currency"/></strong></p>
            <h3>Tổng tiền: 
            <strong  class="cart-all-total" ><fmt:formatNumber value="${cartTotalAllPrice}" type="currency"/></strong>
            </h3>
             <button class="btn btn-success"
        				data-bs-toggle="modal"
        				data-bs-target="#checkoutModal">
 					 Thanh toán
				</button>
            	<jsp:include page="order.jsp"/>
        </div>
    </div>
</div>
<jsp:include page="footer.jsp" />
</body>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/js/bootstrap.min.js"></script>
<script>
document.querySelectorAll(".btn-update").forEach(btn => {
    btn.addEventListener("click", () => {
        const item = btn.closest(".cart-item");
        const bookId = item.dataset.bid;
        const quantity = item.querySelector(".quantity-input").value;
        if(!quantity || quantity <= 0){
    		showToast("Số lượng không hợp lệ hoặc ít hơn 1, hãy nhập lại");
    		return;
    	} 
        fetch("${pageContext.request.contextPath}/cart?action=update&bid="+bookId+"&quantity="+quantity, {
            method: "POST"
        })
        .then(res => res.json())
        .then(data => {
            item.querySelector(".item-total").innerText = data.itemTotalFormatted;
            document.getElementsByClassName("cart-total")[0].innerText = data.cartTotalFormatted;
            document.getElementsByClassName("cart-all-total")[0].innerText = data.cartAllTotalFormatted;
            document.getElementsByClassName("cart-all-total")[1].innerText = data.cartAllTotalFormatted;
            showToast("Đã cập nhật khỏi giỏ hàng thành công");
        });
    });
});

document.querySelectorAll(".btn-remove").forEach(btn => {
    btn.addEventListener("click", () => {
        if (!confirm("Bạn chắc chắn muốn xóa?")) return;
        const item = btn.closest(".cart-item");
        const bookId = item.dataset.bid;
        fetch("${pageContext.request.contextPath}/cart?action=delete&bid="+bookId, {
            method: "POST"
        })
        .then(res => res.json())
        .then(data => {
            item.remove();
            document.getElementsByClassName("cart-total")[0].innerText = data.cartTotalFormatted;
            document.getElementsByClassName("cart-all-total")[0].innerText = data.cartAllTotalFormatted;
            document.getElementsByClassName("cart-all-total")[1].innerText = data.cartAllTotalFormatted;
            showToast("Đã xóa khỏi giỏ hàng thành công");
        });
    });
});
</script>

</html>

