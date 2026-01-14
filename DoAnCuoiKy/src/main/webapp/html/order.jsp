<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<div class="modal fade" id="checkoutModal" tabindex="-1">
	<fmt:setLocale value="vi_VN"/>
  <div class="modal-dialog modal-lg modal-dialog-centered">
    <div class="modal-content">

      <form action="${pageContext.request.contextPath}/order" method="post">
        <div class="modal-header">
          <h5 class="modal-title">Xác nhận thanh toán</h5>
          <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
        </div>

        <div class="modal-body">

          <p>
            <strong>Tổng tiền:</strong>
            <span class="text-danger fw-bold cart-all-total">
              <fmt:formatNumber value="${cartTotalAllPrice}" type="currency"/>
            </span>
          </p>

          <div class="mb-3">
            <label for="name" class="form-label">Họ Tên</label>
            <input type="text" class="form-control" id="fullname" name="fullname" required>
        </div>
        
        
        <div class="mb-3">
            <label for="address" class="form-label">Địa Chỉ Giao Hàng</label>
            <input type="text" class="form-control" id="address" name="address" required>
        </div>
        
        <div class="mb-3">
            <label for="phone" class="form-label">Số Điện Thoại</label>
            <input type="text" class="form-control" id="phone" name="phone" required>
        </div>

        <h4>Hình Thức Thanh Toán</h4>
        <div class="mb-3">
            <select class="form-select" id="paymentOption" name="paymentOption" required>
                <option value="" disabled selected>Chọn hình thức</option>
                <option value="prepay">Tiền mặt</option>
                <option value="postpay">Chuyển khoản</option>
            </select>
        </div>

        <div class="payment-method mb-3" id="paymentMethodDiv">
            <h4>Phương Thức Thanh Toán</h4>
            <select class="form-select" id="paymentMethod">
                <option value="" disabled selected>Chọn phương thức</option>
                <option value="momo">Momo</option>
                <option value="bankTransfer">Ngân hàng</option>
            </select>
             <label for="phone" class="form-label">Số tài khoản</label>
             <input type="text" class="form-control" id="bankAccountNumber">
        </div>

        </div>

        <div class="modal-footer">
          <button type="submit" class="btn btn-success">
            Xác nhận
          </button>
          <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">
            Hủy
          </button>
        </div>

      </form>

    </div>
  </div>
</div>
<script src="${pageContext.request.contextPath}/javascript/paymentOption.js"></script> <!-- Đường dẫn đến tệp JS -->
