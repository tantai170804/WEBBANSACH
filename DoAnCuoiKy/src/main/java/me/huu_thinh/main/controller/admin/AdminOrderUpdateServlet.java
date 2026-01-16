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
		req.setCharacterEncoding("UTF-8");

		try {
			String idStr = req.getParameter("id");
			int orderId = Integer.parseInt(idStr);

			String statusStr = req.getParameter("status");
			int status = Integer.parseInt(statusStr);

			orderService.updateOrderStatus(orderId, status);

			resp.sendRedirect(req.getContextPath() + "/admin/order-detail?id=" + orderId);

		} catch (NumberFormatException e) {
			e.printStackTrace();
			resp.sendRedirect(req.getContextPath() + "/admin/orders");
		} catch (Exception e) {
			e.printStackTrace();
			resp.sendRedirect(req.getContextPath() + "/admin/orders");
		}
	}
}