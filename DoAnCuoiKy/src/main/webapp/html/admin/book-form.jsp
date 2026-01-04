<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Book Form</title>
<style>
body {
	font-family: Arial, sans-serif;
	background: #f5f7fb;
	margin: 0;
	padding: 24px;
	color: #111827;
}

.container {
	max-width: 760px;
	margin: 0 auto;
}

.card {
	background: #fff;
	border: 1px solid #e5e7eb;
	border-radius: 12px;
	box-shadow: 0 6px 18px rgba(0, 0, 0, 0.06);
	padding: 22px;
}

.title {
	font-size: 24px;
	margin: 0 0 16px 0;
	font-weight: 700;
}

.subtitle {
	margin: 0 0 18px 0;
	color: #6b7280;
	font-size: 14px;
}

.form-grid {
	display: grid;
	grid-template-columns: 160px 1fr;
	gap: 12px 14px;
	align-items: center;
}

label {
	font-weight: 600;
	color: #374151;
}

input, select, textarea {
	width: 100%;
	padding: 10px 12px;
	border: 1px solid #d1d5db;
	border-radius: 10px;
	outline: none;
	background: #fff;
	font-size: 14px;
}

input:focus, select:focus, textarea:focus {
	border-color: #2563eb;
	box-shadow: 0 0 0 3px rgba(37, 99, 235, 0.15);
}

textarea {
	min-height: 92px;
	resize: vertical;
}

.row-span {
	grid-column: 1/-1;
	display: flex;
	gap: 10px;
	align-items: center;
}

.actions {
	margin-top: 18px;
	display: flex;
	gap: 10px;
	justify-content: flex-end;
}

.btn {
	border: 0;
	padding: 10px 14px;
	border-radius: 10px;
	cursor: pointer;
	font-weight: 700;
	font-size: 14px;
}

.btn-primary {
	background: #22c55e;
	color: #fff;
}

.btn-primary:hover {
	background: #16a34a;
}

.btn-secondary {
	background: #e5e7eb;
	color: #111827;
	text-decoration: none;
	display: inline-flex;
	align-items: center;
	justify-content: center;
}

.btn-secondary:hover {
	background: #d1d5db;
}

.error-box {
	margin-top: 14px;
	padding: 12px 14px;
	border-radius: 10px;
	background: #fee2e2;
	border: 1px solid #fecaca;
	color: #991b1b;
	font-weight: 600;
}

@media ( max-width : 640px) {
	body {
		padding: 14px;
	}
	.form-grid {
		grid-template-columns: 1fr;
	}
}
</style>
</head>
<body>
	<c:choose>
		<c:when test="${mode == 'edit'}">
			<h2>Sửa sách</h2>
			<form method="post"
				action="${pageContext.request.contextPath}/admin/books/edit">
				<input type="hidden" name="bookId" value="${book.bookId}" />
		</c:when>
		<c:otherwise>
			<h2>Thêm sách</h2>
			<form method="post"
				action="${pageContext.request.contextPath}/admin/books/create">
		</c:otherwise>
	</c:choose>

	Mã sách:
	<input name="bookCode" value="${book.bookCode}" required />
	<br /> Tên sách:
	<input name="title" value="${book.title}" required />
	<br /> Tác giả:
	<input name="author" value="${book.author}" />
	<br /> NXB:
	<input name="publisher" value="${book.publisher}" />
	<br /> Giá:
	<input name="price" value="${book.price}" />
	<br /> Số lượng:
	<input name="quantityInStock" value="${book.quantityInStock}" />
	<br /> Image URL:
	<input name="imageUrl" value="${book.imageUrl}" />
	<br /> Mô tả:
	<textarea name="description">${book.description}</textarea>
	<br />
	<label>Thể loại:</label>
	<select name="categoryId" required>
		<option value="">-- Chọn thể loại --</option>
		<c:forEach items="${categories}" var="c">
			<option value="${c.categoryId}">${c.name}</option>
		</c:forEach>
	</select>
	<br />
	<label>Trạng thái:</label>
	<select name="canShow">
		<option value="1" <c:if test="${book.canShow}">selected</c:if>>Hiển
			thị</option>
		<option value="0" <c:if test="${not book.canShow}">selected</c:if>>Ẩn</option>
	</select>
	<br />
	<button type="submit">Lưu</button>
	</form>

	<c:if test="${not empty error}">
		<p style="color: red">${error}</p>
	</c:if>
</body>
</html>