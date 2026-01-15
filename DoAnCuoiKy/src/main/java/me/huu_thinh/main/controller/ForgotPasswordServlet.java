package me.huu_thinh.main.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import me.huu_thinh.main.model.User;
import me.huu_thinh.main.service.MailService;
import me.huu_thinh.main.service.UserService;

@WebServlet("/forgotpassword")
public class ForgotPasswordServlet extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8606255987166216372L;
	private UserService userservice;
	private MailService mailservice;
	
	public void init() throws ServletException {
		super.init();
		userservice = new UserService();
		mailservice = new MailService();
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getServletContext().getRequestDispatcher("/html/forgotpassword.jsp").forward(request, response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String username = request.getParameter("username");
		
       
		if(username.isBlank()) {
			request.setAttribute("error", "Bạn phải nhập username");
		} else {
			User user = userservice.getUserFromName(username);
			if(user != null) {
				String gmail = user.getEmail();
				try {
					mailservice.sendForgotPassword(gmail, user.getPassword());
					request.setAttribute("error", "Đã gửi mật khẩu qua gmail");
				} catch(Exception ex) {
					request.setAttribute("error", "Lỗi khi cố găng gửi mật khẩu");
				}
			} else {
				request.setAttribute("error", "Không tìm thấy tên đăng nhập của bạn");
			}
		}
		request.setAttribute("username", username);
		request.getRequestDispatcher("/html/forgotpassword.jsp").forward(request, response);
	}
	

}
