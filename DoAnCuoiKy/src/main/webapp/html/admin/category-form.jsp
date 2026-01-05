<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Category Form</title>
<style>
  body{font-family:Arial;background:#f5f7fb;padding:24px}
  .wrap{max-width:700px;margin:0 auto}
  .card{background:#fff;border:1px solid #e5e7eb;border-radius:12px;box-shadow:0 6px 18px rgba(0,0,0,.06);padding:18px}
  h2{margin:0 0 14px}
  label{display:block;font-weight:700;margin-top:10px;color:#374151}
  input,textarea{width:100%;padding:10px 12px;border:1px solid #d1d5db;border-radius:10px}
  textarea{min-height:90px;resize:vertical}
  .actions{margin-top:14px;display:flex;justify-content:flex-end;gap:10px}
  .btn{padding:10px 14px;border-radius:10px;border:0;font-weight:800;cursor:pointer;text-decoration:none;display:inline-flex;align-items:center;justify-content:center}
  .primary{background:#22c55e;color:#fff}
  .primary:hover{background:#16a34a}
  .secondary{background:#e5e7eb;color:#111827}
  .secondary:hover{background:#d1d5db}
  .err{margin-top:12px;padding:10px;border-radius:10px;background:#fee2e2;border:1px solid #fecaca;color:#991b1b;font-weight:700}
</style>
</head>
<body>
<div class="wrap">
  <div class="card">

    <c:choose>
      <c:when test="${mode == 'edit'}">
        <h2>Sửa thể loại</h2>
        <form method="post" action="${pageContext.request.contextPath}/admin/categories/edit">
          <input type="hidden" name="categoryId" value="${category.categoryId}"/>
      </c:when>
      <c:otherwise>
        <h2>Thêm thể loại</h2>
        <form method="post" action="${pageContext.request.contextPath}/admin/categories/create">
      </c:otherwise>
    </c:choose>

      <label>Tên thể loại</label>
      <input name="name" value="${category.name}" required/>

      <label>Mô tả</label>
      <textarea name="description">${category.description}</textarea>

      <div class="actions">
        <a class="btn secondary" href="${pageContext.request.contextPath}/admin/categories">Quay lại</a>
        <button class="btn primary" type="submit">Lưu</button>
      </div>
    </form>

    <c:if test="${not empty error}">
      <div class="err">${error}</div>
    </c:if>

  </div>
</div>
</body>
</html>
