<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
  	<title>Giỏ hàng !</title>
  	<jsp:include page="header.jsp" />
</head>

<body>
<div class="container">
<div class="row">
<div class="col-md-6">
<h3 class="text-center text-sucess">Giỏ hàng của bạn</h3>
<table class="table">
  <thead>
    <tr>
      <th scope="col">Thứ tự</th>
      <th scope="col">Sách ID</th>
      <th scope="col">Số lượng</th>
    </tr>
  </thead>
   
  <tbody>
  	<c:if test="${empty carts}">
      <tr>
        <td colspan="3" class="text-center">
          Giỏ hàng trống
        </td>
      </tr>
    </c:if>
   <c:forEach var="cart" items="${carts}" varStatus="st">
    <tr>
      <td>${st.count}</td>
      <td>${cart.bookId}</td>
      <td>${cart.quantity}</td>
    </tr>
    </c:forEach>
  </tbody>
  
</table>
</div>
</div>
</div>

</body>