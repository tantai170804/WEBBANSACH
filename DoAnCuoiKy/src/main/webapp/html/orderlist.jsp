<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="vi">
<%@ taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<head>
<meta charset="ISO-8859-1">
<title>Xem danh sách đơn hàng</title>
<style>
        body {
            background-color: #f8f9fa;
            color: #343a40;
        }

        .custom-navbar {
            background-color: #343a40;
        }

        .table img {
            height: 50px; /* Kích thước hình ảnh thu nhỏ */
            object-fit: cover;
        }
    </style>
</head>
<body>
<jsp:include page="header.jsp" />
<fmt:setTimeZone value="Etc/GMT0"/>
 <main class="container my-4">
        <h5 class="mb-3">Thông tin đơn hàng</h5>
          <c:if test="${empty orderitems}">
        <div id="order-table">
            <!-- Nếu không có đơn hàng -->
            <p class="text-center">Bạn không có đơn hàng nào.</p>
        </div>
        </c:if>
         <c:if test="${not empty orderitems}">
        <table class="table table-bordered">
            <thead class="thead-dark">
                <tr>
                    <th scope="col">Hình ảnh</th>
                    <th scope="col">Tên sách</th>
                    <th scope="col">Số lượng</th>
                    <th scope="col">Tổng giá</th>
                    <th scope="col">Trạng thái</th>
                    <th scope="col">Đã tạo</th>
                    <th scope="col">Cập nhật lần cuối</th>
                </tr>
            </thead>
            <c:forEach var="orderitem" items="${orderitems}">
            <tbody>
                <tr>
                    <td><img src="${pageContext.request.contextPath}/book_img/${orderitem.imageUrl}" alt="${bookview.book.imageUrl}"></td>
                    <td>${orderitem.bookTitle}</td>
                    <td>${orderitem.quantity}</td>
                    <td>${orderitem.totalPrice}</td>
                    <td>
                    <c:choose>
									<c:when test="${orderitem.status == 'PENDING'}">
										<span class="badge bg-warning">Chờ xử lý</span>
									</c:when>
									<c:when test="${orderitem.status == 'SHIPPING'}">
									<span class="badge bg-primary">Đang giao hàng</span>
									</c:when>
									<c:when test="${orderitem.status == 'SUCCESS'}">
												<span class="badge bg-sucess">Đã giao hàng</span>
									</c:when>
									<c:when test="${orderitem.status == 'CANCEL'}">
										<span class="badge bg-danger">Đã hủy</span>
									</c:when>
									<c:otherwise>
											<span class="badge">${orderitem.status}</span>
									</c:otherwise>
									</c:choose>                    
									</td>
                    <td><fmt:formatDate value="${orderitem.createdAt}" pattern="dd/MM/yyyy HH:mm:ss"/></td>
                    <td><fmt:formatDate value="${orderitem.updatedAt}" pattern="dd/MM/yyyy HH:mm:ss"/></td>
                </tr>
                </c:forEach>
            </tbody>
        </table>
        </c:if>
    </main>
      <jsp:include page="footer.jsp" />
</body>
</html>