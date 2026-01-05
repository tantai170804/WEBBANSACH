<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8" />
  <title>Admin Dashboard</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin.css">
  <style>
    .grid{ display:grid; grid-template-columns: repeat(4, minmax(180px, 1fr)); gap:14px; }
    .stat{ padding:14px; }
    .stat .label{ color:#6b7280; font-weight:800; font-size:13px; }
    .stat .value{ font-size:26px; font-weight:900; margin-top:6px; }
    @media (max-width: 980px){ .grid{ grid-template-columns: repeat(2, 1fr); } }
    @media (max-width: 560px){ .grid{ grid-template-columns: 1fr; } }
  </style>
</head>
<body>
  <div class="admin-layout">
    <jsp:include page="_sidebar.jsp" />

    <div class="content">
      <div class="page-header">
        <div>
          <h1>Dashboard</h1>
          <p class="sub">Tổng quan hệ thống</p>
        </div>
      </div>

      <div class="grid">
        <div class="card stat">
          <div class="label">Tổng sách</div>
          <div class="value">${totalBooks}</div>
        </div>

        <div class="card stat">
          <div class="label">Tổng thể loại</div>
          <div class="value">${totalCategories}</div>
        </div>

        <div class="card stat">
          <div class="label">Tổng người dùng</div>
          <div class="value">${totalUsers}</div>
        </div>

        <div class="card stat">
          <div class="label">Tổng đơn hàng</div>
          <div class="value">${totalOrders}</div>
        </div>
      </div>
    </div>
  </div>
</body>
</html>
