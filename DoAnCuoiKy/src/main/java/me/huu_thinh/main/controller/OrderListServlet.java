package me.huu_thinh.main.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import me.huu_thinh.main.dto.OrderItemViewDTO;
import me.huu_thinh.main.model.User;
import me.huu_thinh.main.service.OrderService;

@WebServlet("/orderlist")
public class OrderListServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2263433387991613276L;
	private OrderService orderservice;
	
	public void init() throws ServletException {
		super.init();
		orderservice = new OrderService();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 HttpSession session = request.getSession();
    	 User user = (User) session.getAttribute("currentUser");
    	 List<OrderItemViewDTO> oiv = orderservice.getOrderItemView(user.getUserId());
    	 request.setAttribute("orderitems", oiv);
		 request.getServletContext().getRequestDispatcher("/html/orderlist.jsp").forward(request, response);
	}

}

