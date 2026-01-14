package me.huu_thinh.main.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import me.huu_thinh.main.model.User;
import me.huu_thinh.main.service.UserService;

@WebServlet("/changepassword")
public class ChangePasswordServlet extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8606255987166216372L;
	private UserService userservice;
	
	public void init() throws ServletException {
		super.init();
		userservice = new UserService();
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getServletContext().getRequestDispatcher("/html/changepassword.jsp").forward(request, response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
	    User user = (User) session.getAttribute("currentUser");
		String password = request.getParameter("password");
		String newpasspassword = request.getParameter("newpassword");
		String comfirmpassword = request.getParameter("confirmpassword");
		ChangePasswordResult result = null;
		if(password.isBlank() || newpasspassword.isBlank() || comfirmpassword.isBlank()) {
			result = new ChangePasswordResult("Đang thiếu dữ liệu, hãy nhập đầy đủ ",1);
		} else {
			int resulttype = userservice.updatePassword(user.getUserId(), password, newpasspassword, comfirmpassword);
			switch(resulttype) {
				case 0:
					result = new ChangePasswordResult("Không tồn tại tài khoản ",1);
					break;
				case 1:
					result = new ChangePasswordResult("Mật khẩu hiện tại của bạn không khớp ",1);
					break;
				case 2:
					result = new ChangePasswordResult("Mật khẩu mới và xác nhận của bạn không khớp ",1);
					break;
				case 3:
					result = new ChangePasswordResult("Mật khẩu mới đang trùng với mật khẩu cũ ",1);
					break;
				case 4:
					result = new ChangePasswordResult("Đã đổi mật khẩu thành công ",0);
					break;
				default:			
		}
		response.setContentType("application/json");
		response.getWriter().print(new Gson().toJson(result));
	}
	}
	
	public static class ChangePasswordResult {
		private String message;
		private int type;
		
		public ChangePasswordResult(String message, int type) {
			this.message = message;
			this.type = type;
		}
		public String getMessage() {
			return message;
		}
		public int getType() {
			return type;
		}
	}
}
