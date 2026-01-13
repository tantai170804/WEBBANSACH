<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="isEdit" value="${mode == 'edit'}" />
<c:set var="pageTitle" value="${isEdit ? 'Cập nhật thông tin' : 'Thêm thể loại mới'}" />
<c:set var="formAction" value="${isEdit ? '/admin/categories/edit' : '/admin/categories/create'}" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${pageTitle} | Quản trị</title>
<style>
/* =========================
   CORE STYLES (Đồng bộ với List)
   ========================= */
:root {
	--bg: #f5f7fb;
	--text: #111827;
	--muted: #6b7280;
	--sidebar: #111827;
	--primary: #2563eb; /* Màu xanh chủ đạo */
	--border: #e5e7eb;
	--shadow: 0 4px 6px -1px rgba(0, 0, 0, 0.1), 0 2px 4px -1px rgba(0, 0, 0, 0.06);
	--danger: #ef4444;
}

body {
	margin: 0;
	font-family: -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto, "Helvetica Neue", Arial, sans-serif;
	background: var(--bg);
	color: var(--text);
}

.admin-layout {
	display: flex;
	min-height: 100vh;
}

.content {
	flex: 1;
	padding: 32px;
	max-width: 100%;
}

/* Sidebar styles (Giữ nguyên để đồng bộ) */
.sidebar { width: 240px; background: var(--sidebar); color: #fff; padding: 20px; flex-shrink: 0; }
/* ... (Bạn có thể include file css chung, ở đây tôi viết inline cho gọn) ... */

/* =========================
   FORM STYLES (Nâng cấp)
   ========================= */
.page-header {
	margin-bottom: 24px;
}
.page-header h1 {
	margin: 0;
	font-size: 24px;
	font-weight: 800;
	color: #111827;
}
.page-header .breadcrumb {
	color: var(--muted);
	font-size: 14px;
	margin-top: 4px;
}

.card {
	background: #fff;
	border: 1px solid var(--border);
	border-radius: 16px;
	box-shadow: var(--shadow);
	max-width: 600px; /* Giới hạn chiều rộng form cho đẹp */
	overflow: hidden;
}

.card-body {
	padding: 24px 32px;
}

.form-group {
	margin-bottom: 20px;
}

.form-label {
	display: block;
	font-weight: 600;
	margin-bottom: 8px;
	color: #374151;
	font-size: 14px;
}

.form-control {
	width: 100%;
	padding: 10px 12px;
	border: 1px solid #d1d5db;
	border-radius: 8px;
	font-size: 15px;
	transition: all 0.2s;
	box-sizing: border-box; /* Quan trọng để không bị vỡ layout */
	font-family: inherit;
}

.form-control:focus {
	outline: none;
	border-color: var(--primary);
	box-shadow: 0 0 0 3px rgba(37, 99, 235, 0.15); /* Hiệu ứng focus xanh */
}

textarea.form-control {
	min-height: 120px;
	resize: vertical;
	line-height: 1.5;
}

.form-actions {
	display: flex;
	align-items: center;
	justify-content: flex-end; /* Nút nằm bên phải */
	gap: 12px;
	margin-top: 32px;
	padding-top: 20px;
	border-top: 1px solid #f3f4f6;
}

.btn {
	display: inline-flex;
	align-items: center;
	justify-content: center;
	padding: 10px 20px;
	border-radius: 8px;
	font-weight: 600;
	text-decoration: none;
	cursor: pointer;
	border: 1px solid transparent;
	font-size: 14px;
	transition: all 0.2s;
}

.btn-primary {
	background: var(--primary);
	color: #fff;
	box-shadow: 0 1px 2px 0 rgba(0, 0, 0, 0.05);
}
.btn-primary:hover {
	background: #1d4ed8;
	transform: translateY(-1px);
}

.btn-secondary {
	background: #fff;
	border-color: #d1d5db;
	color: #374151;
}
.btn-secondary:hover {
	background: #f9fafb;
	border-color: #9ca3af;
}

/* Alert Error */
.alert-error {
	background: #fef2f2;
	border: 1px solid #fecaca;
	color: #991b1b;
	padding: 12px;
	border-radius: 8px;
	margin-bottom: 20px;
	font-size: 14px;
	display: flex;
	align-items: center;
	gap: 8px;
}
</style>
</head>
<body>

<div class="admin-layout">
	<div class="content">
		
		<div class="page-header">
			<h1>${pageTitle}</h1>
			<div class="breadcrumb">
				Quản lý thể loại &rsaquo; <span style="color: var(--primary)">${isEdit ? 'Sửa' : 'Tạo mới'}</span>
			</div>
		</div>

		<c:if test="${not empty error}">
			<div class="alert-error">
				<svg width="20" height="20" fill="none" viewBox="0 0 24 24" stroke="currentColor">
				  <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M12 8v4m0 4h.01M21 12a9 9 0 11-18 0 9 9 0 0118 0z" />
				</svg>
				<span>${error}</span>
			</div>
		</c:if>

		<div class="card">
			<div class="card-body">
				
				<form method="post" action="${pageContext.request.contextPath}${formAction}">
					
					<c:if test="${isEdit}">
						<input type="hidden" name="categoryId" value="${category.categoryId}" />
					</c:if>

					<div class="form-group">
						<label class="form-label" for="inpName">Tên thể loại <span style="color:red">*</span></label>
						<input type="text" id="inpName" name="name" 
							   class="form-control" 
							   value="${category.name}" 
							   placeholder="Ví dụ: Sách Văn Học, Tiểu Thuyết..."
							   required autofocus />
					</div>

					<div class="form-group">
						<label class="form-label" for="inpDesc">Mô tả</label>
						<textarea id="inpDesc" name="description" 
								  class="form-control" 
								  placeholder="Nhập mô tả ngắn gọn về thể loại này...">${category.description}</textarea>
					</div>

					<div class="form-actions">
						<a href="${pageContext.request.contextPath}/admin/categories" class="btn btn-secondary">
							Hủy bỏ
						</a>
						<button type="submit" class="btn btn-primary">
							${isEdit ? 'Lưu thay đổi' : 'Tạo thể loại'}
						</button>
					</div>

				</form>
			</div>
		</div>
		</div>
</div>

</body>
</html>