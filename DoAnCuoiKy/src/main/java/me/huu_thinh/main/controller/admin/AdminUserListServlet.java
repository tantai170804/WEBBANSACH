package me.huu_thinh.main.controller.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import me.huu_thinh.main.dao.UserDAO;
import me.huu_thinh.main.model.User;

@WebServlet("/admin/users")
public class AdminUserListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		UserDAO dao = new UserDAO();

		int page = 1; 
		int limit = 10; 
		if (request.getParameter("page") != null) {
			try {
				page = Integer.parseInt(request.getParameter("page"));
				if (page < 1)
					page = 1; 
			} catch (NumberFormatException e) {
				page = 1;
			}
		}

		int offset = (page - 1) * limit;

		try {
			int totalItems = dao.countAll(); 
			List<User> list = dao.findAll(limit, offset); 

			int totalPages = (int) Math.ceil((double) totalItems / limit);

			request.setAttribute("users", list);
			request.setAttribute("currentPage", page); 
			request.setAttribute("totalPages", totalPages); 

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", "Lỗi lấy dữ liệu: " + e.getMessage());
		}

		request.getRequestDispatcher("/html/admin/user-list.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}