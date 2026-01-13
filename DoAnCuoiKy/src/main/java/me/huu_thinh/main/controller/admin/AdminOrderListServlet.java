package me.huu_thinh.main.controller.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
// import javax.servlet.http.HttpSession; 
// import me.huu_thinh.main.model.User;

import me.huu_thinh.main.model.Order;
import me.huu_thinh.main.service.OrderService;

// Đường dẫn: http://localhost:8080/TenProject/admin/orders?page=1
@WebServlet(name = "AdminOrderListServlet", urlPatterns = { "/admin/orders" })
public class AdminOrderListServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	// Khởi tạo Service
	private OrderService orderService = new OrderService();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// --- BƯỚC 1: Xử lý số trang (Page) ---
		// Mặc định là trang 1 nếu không có tham số hoặc tham số lỗi
		int page = 1;
		try {
			String pageStr = req.getParameter("page");
			if (pageStr != null) {
				page = Integer.parseInt(pageStr);
			}
		} catch (NumberFormatException e) {
			page = 1;
		}

		// --- BƯỚC 2: Thiết lập cấu hình phân trang ---
		int limit = 10; // Số lượng đơn hàng hiển thị trên 1 trang
		int offset = (page - 1) * limit; // Vị trí bắt đầu lấy trong DB

		// --- BƯỚC 3: Gọi Service lấy dữ liệu phân trang ---
		// Lấy danh sách đơn hàng giới hạn theo limit và offset
		List<Order> orderList = orderService.getOrdersPagination(limit, offset);

		// Lấy tổng số lượng đơn hàng trong DB để tính tổng số trang
		int totalItems = orderService.getTotalOrders();

		// Tính tổng số trang (làm tròn lên). Ví dụ: 11 items / 10 limit = 1.1 -> Lên 2
		// trang
		int totalPages = (int) Math.ceil((double) totalItems / limit);

		// --- BƯỚC 4: Đẩy dữ liệu sang JSP ---
		req.setAttribute("orders", orderList); // Danh sách đơn hàng (chỉ 10 cái)
		req.setAttribute("currentPage", page); // Trang hiện tại (để tô màu nút)
		req.setAttribute("totalPages", totalPages); // Tổng số trang (để vẽ vòng lặp nút)

		// --- BƯỚC 5: Chuyển hướng về giao diện ---
		req.getRequestDispatcher("/html/admin/order-list.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// Chuyển mọi request POST về GET để xử lý hiển thị
		doGet(req, resp);
	}
}