<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="vi">
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
            <div class="row my-3 border-bottom pb-3">
                <div class="col-2">
                    <img src="${pageContext.request.contextPath}/book_img/${cartitem.bookImageUrl}" class="img-thumbnail">
                </div>
                <div class="col-6">
                    <p><strong>Tên sách: </strong>${cartitem.bookTitle}</p>
                    <p>
                        <strong>Số lượng:</strong>
                        <input type="number" class="form-control d-inline w-25" value="${cartitem.quantity}" min="1"/>
                    </p>
                    <p>
                        <strong >Tổng cộng giá: 
                        	<fmt:formatNumber value="${cartitem.totalPrice}" type="currency"/>
                        </strong>
                    </p>
                    <button class="btn btn-warning btn-sm ms-1">Cập nhật</button>
                    <button class="btn btn-danger btn-sm ms-2">Loại Bỏ</button>
                </div>
            </div>
            </c:forEach>
        </div>

        <div class="col-md-4 p-4 border-start">
            <h2>Bảng Thanh Toán</h2>
            <p>Tổng cộng: <strong><fmt:formatNumber value="${cartTotalBookPrice}" type="currency"/></strong></p>
            <p>Phí vận chuyển: <strong><fmt:formatNumber value="30000" type="currency"/></strong></p>
            <h3>Tổng tiền: <strong><fmt:formatNumber value="${cartTotalAllPrice}" type="currency"/></strong></h3>
             <button class="btn btn-success"
        				data-bs-toggle="modal"
        				data-bs-target="#checkoutModal">
 					 Thanh toán
				</button>
            	<jsp:include page="order.jsp"/>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/javascript/paymentOption.js"></script> <!-- Đường dẫn đến tệp JS -->
</body>
</html>