<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="activePage" value="books" scope="request" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Admin - Book Form</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/admin.css">
</head>

<body>
	<div class="admin-layout">

		<jsp:include page="_sidebar.jsp" />

		<div class="content">

			<!-- HEADER -->
			<div class="page-header">
				<div>
					<h1>
						<c:choose>
							<c:when test="${mode == 'edit'}">Sửa sách</c:when>
							<c:otherwise>Thêm sách</c:otherwise>
						</c:choose>
					</h1>
					<p class="sub">Quản lý thông tin sách trong hệ thống</p>
				</div>
			</div>

			<!-- ERROR -->
			<c:if test="${not empty error}">
				<div class="alert alert-danger">${error}</div>
			</c:if>

			<!-- FORM -->
			<div class="card">
				<div class="card-body">

					<form method="post"
						action="${pageContext.request.contextPath}/admin/books/${mode}">

						<c:if test="${mode == 'edit'}">
							<input type="hidden" name="bookId" value="${book.bookId}">
						</c:if>

						<div class="form-grid">

							<label>Mã sách</label> <input name="bookCode"
								value="${book.bookCode}" required> <label>Tên
								sách</label> <input name="title" value="${book.title}" required>

							<label>Tác giả</label> <input name="author"
								value="${book.author}"> <label>Nhà xuất bản</label> <input
								name="publisher" value="${book.publisher}"> <label>Giá</label>
							<input name="price" value="${book.price}" type="number"
								step="0.01"> <label>Số lượng</label> <input
								name="quantityInStock" value="${book.quantityInStock}"
								type="number"> <label>Image URL</label> <input
								name="imageUrl" value="${book.imageUrl}"> <label>Thể
								loại</label> <select name="categoryId" required>
								<option value="">-- Chọn thể loại --</option>
								<c:forEach items="${categories}" var="c">
									<option value="${c.categoryId}"
										<c:if test="${c.categoryId == book.categoryId}">selected</c:if>>
										${c.name}</option>
								</c:forEach>
							</select> <label>Trạng thái</label> <select name="canShow">
								<option value="1" ${book.canShow ? 'selected' : ''}>Hiển
									thị</option>
								<option value="0" ${!book.canShow ? 'selected' : ''}>Ẩn</option>
							</select> <label>Mô tả</label>
							<textarea name="description">${book.description}</textarea>

						</div>

						<!-- ACTIONS -->
						<div class="actions">
							<button type="submit" class="btn btn-primary">Lưu</button>

							<a class="btn btn-outline"
								href="${pageContext.request.contextPath}/admin/books"> Hủy </a>
						</div>

					</form>

				</div>
			</div>

		</div>
	</div>
</body>
</html>
