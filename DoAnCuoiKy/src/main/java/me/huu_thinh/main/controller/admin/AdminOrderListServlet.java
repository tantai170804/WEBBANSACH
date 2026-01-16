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


@WebServlet(name = "AdminOrderListServlet", urlPatterns = { "/admin/orders" })
public class AdminOrderListServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	// Khởi tạo Service
	private OrderService orderService = new OrderService();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		int page = 1;
		try {
			String pageStr = req.getParameter("page");
			if (pageStr != null) {
				page = Integer.parseInt(pageStr);
			}
		} catch (NumberFormatException e) {
			page = 1;
		}

		int limit = 10; 
		int offset = (page - 1) * limit; 

		List<Order> orderList = orderService.getOrdersPagination(limit, offset);

		int totalItems = orderService.getTotalOrders();

		int totalPages = (int) Math.ceil((double) totalItems / limit);

		req.setAttribute("orders", orderList); 
		req.setAttribute("currentPage", page); 
		req.setAttribute("totalPages", totalPages);

		req.getRequestDispatcher("/html/admin/order-list.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}