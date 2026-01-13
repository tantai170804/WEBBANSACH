package me.huu_thinh.main.controller.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import me.huu_thinh.main.model.Order;
import me.huu_thinh.main.model.OrderItem;
import me.huu_thinh.main.service.OrderService;

@WebServlet(name = "AdminOrderDetailServlet", urlPatterns = "/admin/order-detail")
public class AdminOrderDetailServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private OrderService orderService = new OrderService();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			// 1. Lấy ID đơn hàng từ tham số URL (VD: ...?id=5)
			String idStr = req.getParameter("id");

			// Kiểm tra nếu id null hoặc rỗng thì quay về trang danh sách
			if (idStr == null || idStr.isEmpty()) {
				resp.sendRedirect(req.getContextPath() + "/admin/orders");
				return;
			}

			int orderId = Integer.parseInt(idStr);

			// 2. Lấy thông tin đơn hàng (Order)
			Order order = orderService.getOrderById(orderId);

			// Nếu không tìm thấy đơn hàng (ví dụ id=9999 không tồn tại)
			if (order == null) {
				resp.sendRedirect(req.getContextPath() + "/admin/orders");
				return;
			}

			// 3. Lấy danh sách sản phẩm (Items)
			// Lưu ý: Tên hàm phải khớp với OrderService (getOrderItems)
			List<OrderItem> items = orderService.getOrderItems(orderId);

			// 4. Đẩy dữ liệu sang JSP
			req.setAttribute("order", order);
			req.setAttribute("items", items);

			// 5. Chuyển hướng sang file giao diện chi tiết
			req.getRequestDispatcher("/html/admin/order-detail.jsp").forward(req, resp);

		} catch (NumberFormatException e) {
			// Nếu ID không phải số
			resp.sendRedirect(req.getContextPath() + "/admin/orders");
		} catch (Exception e) {
			e.printStackTrace();
			resp.sendRedirect(req.getContextPath() + "/admin/orders");
		}
	}
}