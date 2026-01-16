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
			String idStr = req.getParameter("id");

			if (idStr == null || idStr.isEmpty()) {
				resp.sendRedirect(req.getContextPath() + "/admin/orders");
				return;
			}

			int orderId = Integer.parseInt(idStr);


			Order order = orderService.getOrderById(orderId);

			if (order == null) {
				resp.sendRedirect(req.getContextPath() + "/admin/orders");
				return;
			}

			List<OrderItem> items = orderService.getOrderItems(orderId);

			req.setAttribute("order", order);
			req.setAttribute("items", items);

			req.getRequestDispatcher("/html/admin/order-detail.jsp").forward(req, resp);

		} catch (NumberFormatException e) {
			resp.sendRedirect(req.getContextPath() + "/admin/orders");
		} catch (Exception e) {
			e.printStackTrace();
			resp.sendRedirect(req.getContextPath() + "/admin/orders");
		}
	}
}