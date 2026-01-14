package me.huu_thinh.main.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import me.huu_thinh.main.model.Order;
import me.huu_thinh.main.model.User;
import me.huu_thinh.main.service.OrderService;

@WebServlet("/order")
public class OrderServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2415081691047669680L;
	private OrderService orderservice;
	
		public void init() throws ServletException {
			super.init();
			orderservice = new OrderService();
		}
	
	   protected void doGet(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
	   }
	   protected void doPost(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
		   System.out.println("Post checking order");
		   HttpSession session = request.getSession();
		   User user = (User) session.getAttribute("currentUser");
	       
	       boolean issucess = handleCheckout(user,request);
	       response.sendRedirect(request.getContextPath() + "/cart"); 
	       if(issucess) {
	    	   session.setAttribute("snackbarMsg", "Đã thanh toán thành công!");
		       session.setAttribute("snackbarType", "success");
	       } else {
	    	   session.setAttribute("snackbarMsg", "Đã thanh toán thành công!");
		       session.setAttribute("snackbarType", "error");
	       }
	   }

	private boolean handleCheckout(User user, HttpServletRequest request) {
		String fullname = request.getParameter("fullname");
		String address = request.getParameter("address");
		String phone = request.getParameter("phone");
		String paymentOption = request.getParameter("paymentOption");
		
		Order order = new Order();
		
		order.setUserId(user.getUserId());
		order.setFullName(fullname);
		order.setAddress(address);
		order.setPhone(phone);
		order.setPaymentMethod(paymentOption);
		
		return orderservice.checkout(user, order);
		
	}

}