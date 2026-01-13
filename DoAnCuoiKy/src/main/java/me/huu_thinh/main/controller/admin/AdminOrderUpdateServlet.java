package me.huu_thinh.main.controller.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import me.huu_thinh.main.service.OrderService;

@WebServlet(name = "AdminOrderUpdateServlet", urlPatterns = "/admin/order-update")
public class AdminOrderUpdateServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	// Gọi Service
	private OrderService orderService = new OrderService();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 1. Đặt encoding UTF-8
		req.setCharacterEncoding("UTF-8");

		try {
			// 2. Lấy ID đơn hàng từ form (hidden input)
			String idStr = req.getParameter("id");
			int orderId = Integer.parseInt(idStr);

			// 3. Lấy Status từ form (Select option)
			// LƯU Ý: DAO của bạn lưu status là số (int), nên ta phải ép kiểu về int
			// HTML gửi lên: "0", "1", "2"... -> Java parse thành số 0, 1, 2...
			String statusStr = req.getParameter("status");
			int status = Integer.parseInt(statusStr);

			// 4. Gọi Service để update
			// Hàm này bên Service nhận vào (int orderId, int status)
			orderService.updateOrderStatus(orderId, status);

			// 5. Cập nhật xong thì quay lại trang chi tiết đơn hàng đó
			// Lưu ý: Đường dẫn "/admin/order-details" phải khớp với Servlet xem chi tiết mà
			// bạn đã/sẽ viết
			resp.sendRedirect(req.getContextPath() + "/admin/order-details?id=" + orderId);

		} catch (NumberFormatException e) {
			e.printStackTrace();
			// Nếu lỗi ép kiểu số, quay về danh sách đơn hàng
			resp.sendRedirect(req.getContextPath() + "/admin/orders");
		} catch (Exception e) {
			e.printStackTrace();
			resp.sendRedirect(req.getContextPath() + "/admin/orders");
		}
	}
}