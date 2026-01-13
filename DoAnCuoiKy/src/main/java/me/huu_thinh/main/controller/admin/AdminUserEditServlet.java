package me.huu_thinh.main.controller.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import me.huu_thinh.main.dao.UserDAO;
import me.huu_thinh.main.model.User;
import me.huu_thinh.main.service.UserService;

@WebServlet("/admin/users/edit")
public class AdminUserEditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private final UserService userService = new UserService();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Integer id = parseInt(request.getParameter("id"));
		if (id == null || id <= 0) {
			response.sendRedirect(request.getContextPath() + "/admin/users");
			return;
		}

		User user = UserDAO.findById(id);
		if (user == null) {
			response.sendRedirect(request.getContextPath() + "/admin/users");
			return;
		}

		request.setAttribute("user", user);
		request.getRequestDispatcher("/html/admin/user-form.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		Integer userId = parseInt(request.getParameter("userId"));
		if (userId == null || userId <= 0) {
			response.sendRedirect(request.getContextPath() + "/admin/users");
			return;
		}

		User u = new User();
		u.setUserId(userId);
		u.setEmail(trim(request.getParameter("email")));
		u.setPhone(trim(request.getParameter("phone")));
		u.setAddress(trim(request.getParameter("address")));
		u.setRole(trim(request.getParameter("role")));

		boolean ok = userService.update(u);
		if (!ok) {
			request.setAttribute("error", "Cập nhật user thất bại!");
			request.setAttribute("user", UserDAO.findById(userId));
			request.getRequestDispatcher("/html/admin/user-form.jsp").forward(request, response);
			return;
		}

		response.sendRedirect(request.getContextPath() + "/admin/users");
	}

	/* ================== helpers ================== */  

	private Integer parseInt(String s) {
		if (s == null || s.isBlank())
			return null;
		try {
			return Integer.parseInt(s.trim());
		} catch (NumberFormatException e) {
			return null;
		}
	}

	private String trim(String s) {
		return s == null ? null : s.trim();
	}
}
