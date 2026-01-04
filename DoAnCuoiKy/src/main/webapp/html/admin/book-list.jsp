<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Admin - Book List</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/admin.css">
</head>
<body>
	<div class="admin-layout">
	<c:set var="activePage" value="books" scope="request" />
		<jsp:include page="_sidebar.jsp" />

		<div class="content">
			<div class="page-header">
				<div>
					<h1>Danh sách sách</h1>
					<p class="sub">Quản lý danh mục sách trong hệ thống</p>
				</div>

				<a class="btn btn-primary"
					href="${pageContext.request.contextPath}/admin/books/create"> +
					Thêm sách </a>
			</div>

			<div class="card">
				<div class="card-body">
					<table class="table">
						<thead>
							<tr>
								<th>ID</th>
								<th>Mã</th>
								<th>Tên</th>
								<th>Thể loại</th>
								<th class="text-right">Giá</th>
								<th class="text-center">SL</th>
								<th>Trạng thái</th>
								<th class="text-right">Hành động</th>
							</tr>
						</thead>

						<tbody>
							<c:forEach items="${books}" var="b">
								<tr>
									<td>${b.bookId}</td>
									<td><span class="badge">${b.bookCode}</span></td>
									<td class="title"><c:out value="${b.title}" /></td>
									<td><c:out value="${b.categoryName}" /></td>
									<td class="text-right"><fmt:formatNumber
											value="${b.price}" type="number" groupingUsed="true" /></td>
									<td class="text-center">${b.quantityInStock}</td>

									<td><c:choose>
											<c:when test="${b.canShow}">
												<span class="pill pill-green">Hiển thị</span>
											</c:when>
											<c:otherwise>
												<span class="pill pill-gray">Ẩn</span>
											</c:otherwise>
										</c:choose></td>

									<td class="text-right"><a class="btn btn-sm btn-outline"
										href="${pageContext.request.contextPath}/admin/books/edit?id=${b.bookId}">
											Sửa </a>

										<form method="post"
											action="${pageContext.request.contextPath}/admin/books/delete"
											style="display: inline">
											<input type="hidden" name="id" value="${b.bookId}" />
											<button class="btn btn-sm btn-danger" type="submit"
												onclick="return confirm('Xóa sách này?')">Xóa</button>
										</form></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>

			<!-- Pagination nên nằm trong content để canh lề đẹp -->
			<div class="pagination">
				<c:set var="base"
					value="${pageContext.request.contextPath}/admin/books?size=${size}&page=" />

				<c:if test="${page > 1}">
					<a class="page-btn" href="${base}${page-1}">« Trước</a>
				</c:if>

				<c:forEach begin="1" end="${totalPages}" var="p">
					<c:choose>
						<c:when test="${p == page}">
							<span class="page-btn active">${p}</span>
						</c:when>
						<c:otherwise>
							<a class="page-btn" href="${base}${p}">${p}</a>
						</c:otherwise>
					</c:choose>
				</c:forEach>

				<c:if test="${page < totalPages}">
					<a class="page-btn" href="${base}${page+1}">Sau »</a>
				</c:if>

				<span class="page-meta">Tổng: ${totalItems} sách</span>
			</div>
		</div>
	</div>
</body>

</html>
