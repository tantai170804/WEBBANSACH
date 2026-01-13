<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="activePage" value="books" scope="request" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Admin - ${mode == 'edit' ? 'Cập nhật sách' : 'Thêm sách mới'}</title>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin.css">

<style>
    /* Grid layout cho form: Nhãn bên trên, ô nhập bên dưới */
    .form-grid {
        display: grid;
        gap: 16px;
        max-width: 800px; /* Giới hạn chiều rộng cho dễ nhìn */
    }

    /* Style cho nhãn (Label) */
    label {
        font-weight: 700;
        font-size: 14px;
        color: #374151;
        margin-bottom: 4px;
        display: block;
    }
    
    /* Style cho ô nhập liệu (Input, Select, Textarea) */
    input[type="text"],
    input[type="number"],
    select,
    textarea {
        width: 100%;
        padding: 10px 12px;
        border: 1px solid #d1d5db;
        border-radius: 8px;
        font-size: 14px;
        font-family: inherit;
        outline: none;
        transition: border-color 0.2s;
        background: #fff;
    }

    input:focus, select:focus, textarea:focus {
        border-color: #2563eb; /* Màu xanh khi bấm vào */
        box-shadow: 0 0 0 3px rgba(37, 99, 235, 0.1);
    }

    textarea {
        min-height: 100px;
        resize: vertical;
    }

    /* Nhóm các nút bấm bên dưới */
    .actions {
        margin-top: 24px;
        display: flex;
        gap: 12px;
        padding-top: 20px;
        border-top: 1px solid #e5e7eb;
    }
    
    /* Chia cột cho các trường ngắn (Giá, Số lượng...) */
    .form-row {
        display: grid;
        grid-template-columns: 1fr 1fr;
        gap: 16px;
    }
</style>
</head>

<body>
    <div class="admin-layout">

        <div class="content">

            <div class="page-header">
                <div>
                    <h1>
                        <c:choose>
                            <c:when test="${mode == 'edit'}">Cập nhật thông tin</c:when>
                            <c:otherwise>Thêm sách mới</c:otherwise>
                        </c:choose>
                    </h1>
                    <p class="sub">Vui lòng điền đầy đủ thông tin bên dưới</p>
                </div>
            </div>

            <c:if test="${not empty error}">
                <div class="alert alert-danger">${error}</div>
            </c:if>

            <div class="card">
                <div class="card-body">

                    <form method="post" action="${pageContext.request.contextPath}/admin/books/${mode}">

                        <c:if test="${mode == 'edit'}">
                            <input type="hidden" name="bookId" value="${book.bookId}">
                        </c:if>

                        <div class="form-grid">
                            
                            <div>
                                <label>Tên sách <span style="color:red">*</span></label>
                                <input type="text" name="title" value="${book.title}" required placeholder="Ví dụ: Đắc Nhân Tâm">
                            </div>
                            
                            <div class="form-row">
                                <div>
                                    <label>Mã sách (ISBN) <span style="color:red">*</span></label>
                                    <input type="text" name="bookCode" value="${book.bookCode}" required placeholder="SP001">
                                </div>
                                <div>
                                    <label>Tác giả</label>
                                    <input type="text" name="author" value="${book.author}" placeholder="Tên tác giả">
                                </div>
                            </div>

                            <div class="form-row">
                                <div>
                                    <label>Nhà xuất bản</label>
                                    <input type="text" name="publisher" value="${book.publisher}">
                                </div>
                                <div>
                                    <label>Thể loại <span style="color:red">*</span></label>
                                    <select name="categoryId" required>
                                        <option value="">-- Chọn thể loại --</option>
                                        <c:forEach items="${categories}" var="c">
                                            <option value="${c.categoryId}" <c:if test="${c.categoryId == book.categoryId}">selected</c:if>>
                                                ${c.name}
                                            </option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>

                            <div class="form-row">
                                <div>
                                    <label>Giá bán (VNĐ)</label>
                                    <input type="number" name="price" value="${book.price}" min="0" step="1000">
                                </div>
                                <div>
                                    <label>Số lượng trong kho</label>
                                    <input type="number" name="quantityInStock" value="${book.quantityInStock}" min="0">
                                </div>
                            </div>

                            <div>
                                <label>Link ảnh bìa (URL)</label>
                                <input type="text" name="imageUrl" value="${book.imageUrl}" placeholder="https://...">
                            </div>

                            <div>
                                <label>Trạng thái hiển thị</label>
                                <select name="canShow">
                                    <option value="1" ${book.canShow ? 'selected' : ''}>Công khai (Đang bán)</option>
                                    <option value="0" ${!book.canShow ? 'selected' : ''}>Ẩn (Tạm ngưng)</option>
                                </select>
                            </div>

                            <div>
                                <label>Mô tả chi tiết</label>
                                <textarea name="description" placeholder="Nhập nội dung mô tả sách...">${book.description}</textarea>
                            </div>
                        </div>

                        <div class="actions">
                            <button type="submit" class="btn btn-primary">
                                <c:choose>
                                    <c:when test="${mode == 'edit'}">Lưu thay đổi</c:when>
                                    <c:otherwise>Tạo mới</c:otherwise>
                                </c:choose>
                            </button>

                            <a class="btn btn-outline" href="${pageContext.request.contextPath}/admin/books">
                                Quay lại
                            </a>
                        </div>

                    </form>
                </div>
            </div>
        </div>
    </div>
</body>
</html>